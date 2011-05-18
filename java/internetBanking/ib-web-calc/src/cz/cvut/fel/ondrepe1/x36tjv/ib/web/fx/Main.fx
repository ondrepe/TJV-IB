package cz.cvut.fel.ondrepe1.x36tjv.ib.web.fx;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Tile;
import javafx.io.http.HttpRequest;
import java.io.InputStream;
import javafx.data.pull.PullParser;
import javafx.data.pull.Event;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javafx.scene.layout.VBox;

var currencyFromBox = TextBox {
          text: ""
          columns: 3
          selectOnFocus: true
        }
var currencyFromErrorLabel = Label {
          text: ""
        }
var currencyToBox = TextBox {
          text: ""
          columns: 3
          selectOnFocus: true
        }
var currencyToErrorLabel = Label {
          text: ""
        }
var amountBox = TextBox {
          text: ""
          columns: 23
          selectOnFocus: true
        }
var amountErrorLabel = Label {
          text: ""
        }
var resultBox = TextBox {
          text: ""
          columns: 12
          editable: false
        }
var amount;

Stage {
  title: "Convert Money"
  scene: Scene {
    width: 540
    height: 120
    content: [
      VBox {
        content: [
          Tile {
            rows: 4
            columns: 3
            content: [
              [Label {
                  text: "Amount:"
                }
                amountBox,
                amountErrorLabel
              ],
              [
                Label {
                  text: "Currency code from:"
                }
                currencyFromBox,
                currencyFromErrorLabel
              ],
              [
                Label {
                  text: "Currency code to:"
                }
                currencyToBox,
                currencyToErrorLabel
              ],
              [
                Label {
                  text: "Result:"
                }
                resultBox,
                Button {
                  text: "Convert"
                  action: function() {
                    var error = false;
                    resultBox.text = "";
                    amountErrorLabel.text = "";
                    currencyFromErrorLabel.text = "";
                    currencyToErrorLabel.text = "";
                    amountBox.text = amountBox.text.trim();
                    currencyFromBox.text = currencyFromBox.text.trim();
                    currencyFromBox.text = currencyFromBox.text.toUpperCase();
                    currencyToBox.text = currencyToBox.text.trim();
                    currencyToBox.text = currencyToBox.text.toUpperCase();

                    if (amountBox.text.matches("^\\d\{0,18\}[\\.,\\,]?\\d\{0,4\}$") == false) {
                      amountErrorLabel.text = "Bad amount value!";
                      error = true;
                    } else if(amountBox.text == null) {
                      amountErrorLabel.text = "Amount is mandatory!";
                    } else {
                      amount = new BigDecimal(amountBox.text);
                    }
                    if (currencyFromBox.text.length() != 3) {
                      currencyFromErrorLabel.text = "The code must be 3 characters!";
                      error = true;
                       }
                    if (currencyToBox.text.length() != 3) {
                      currencyToErrorLabel.text = "The code must be 3 characters!";
                      error = true;
                       }
                    if (error) {
                      return;
                       }
                    def request = HttpRequest {
                              location: "http://localhost:8080/ExchangeOffice/exchangerate/{currencyFromBox.text}{currencyToBox.text}"
                              method: HttpRequest.GET
                              onResponseCode: function(respCode: Integer) {
                                if(respCode == 400)
                                  resultBox.text = "Bad currency code!";
                              }
                              onInput: function(is: InputStream) {
                                try {
                                  def parser = PullParser {
                                            input: is
                                            onEvent: function(e: Event) {
//                                                                if (e.type == PullParser.TEXT and e.qname.name == "ticker") {
//                                                                    tickerBox.text = e.text;
//                                                                }
                                              if (e.type == PullParser.TEXT and e.qname.name == "exchangeRate") {
                                                var rate = new BigDecimal(e.text);
                                                var result = amount.multiply(rate, new MathContext(24, RoundingMode.HALF_UP));
                                                result = result.setScale(4, RoundingMode.HALF_UP);
                                                var strResult = result.toString();
                                                resultBox.text = "{strResult} {currencyToBox.text}";

                                                 }
                                              }
                                          }
                                  parser.parse();
                                   } finally {
                                  is.close();
                                   }
                                }
                            }
                    request.start();
                    }
                }
              ]
            ]
          }
        ]
      }
    ]
  }
}
