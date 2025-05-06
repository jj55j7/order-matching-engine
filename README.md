# Order Matching Engine (Java)

A simple command-line application to simulate an order matching system for BUY/SELL trades.

## How It Works

- Users input orders in the form: `BUY 10 100.0`
- Orders are matched using priority queues based on price and time
- Matching logic:
  - BUY matches with lowest-price SELL
  - SELL matches with highest-price BUY
  - Ties are broken by timestamp (FIFO)

## How to Run

Compile all Java files:
```bash
javac *.java
