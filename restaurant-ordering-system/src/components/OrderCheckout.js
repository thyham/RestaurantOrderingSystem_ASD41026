import React, { useState, useEffect } from "react";

function OrderCheckout() {
  const [cartItems, setCartItems] = useState([]);
  const [paymentMethod, setPaymentMethod] = useState("");
  const [orderConfirmed, setOrderConfirmed] = useState(false);

  useEffect(() => {
    // Mock cart data for testing
    const mockCartItems = [
      { id: 1, name: 'Pizza', price: 10 },
      { id: 2, name: 'Burger', price: 8 }
    ];

    setCartItems(mockCartItems);
  }, []);

  const handlePayment = () => {
    // Simulate payment processing
    setOrderConfirmed(true);
  };

  if (orderConfirmed) {
    return <h2>Order Confirmed! Thank you for your purchase.</h2>;
  }

  return (
    <div>
      <h1>Order Checkout</h1>
      <h2>Review Cart</h2>
      <ul>
        {cartItems.map((item) => (
          <li key={item.id}>
            {item.name} - ${item.price}
          </li>
        ))}
      </ul>

      <h2>Choose Payment Method</h2>
      <select
        value={paymentMethod}
        onChange={(e) => setPaymentMethod(e.target.value)}
      >
        <option value="">Select a payment method</option>
        <option value="credit">Credit Card</option>
        <option value="paypal">PayPal</option>
      </select>

      <button onClick={handlePayment}>Confirm Order</button>
    </div>
  );
}

export default OrderCheckout;
