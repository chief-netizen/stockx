Feature:Portfolio report

  Scenario:Calculate average price and profit

    Given the following stocks exist
      | ticker | quantity | tradeType | unitPrice | tradeDate  |
      | AAPL   | 20       | BUY       | 300       | 2026-07-05 |
      | AAPL   | 22       | BUY       | 310       | 2026-07-06 |
      | AAPL   | 19       | SELL      | 325       | 2026-07-07 |

    Then ticker "AAPL" should have 309.56522 AveragePrice and 475 Profit
