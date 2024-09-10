import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';
import OrderCheckout from './components/OrderCheckout';  // Import OrderCheckout component
import OrderHistory from './components/OrderHistory';    // Import OrderHistory component

function App() {
  return (
    <Router>
      <div>
        <header>
          <nav>
            <Link to="/checkout">Order Checkout</Link> |
            <Link to="/order-history">Order History</Link>
          </nav>
        </header>
        <Routes>
          <Route path="/checkout" element={<OrderCheckout />} />
          <Route path="/order-history" element={<OrderHistory />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;