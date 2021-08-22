import React, {useEffect, useState} from "react";
import {CardElement, useStripe, useElements} from "@stripe/react-stripe-js";
import axios from 'axios'

const CardForm = () => {
    const [succeeded, setSucceeded] = useState(false);
    const [error, setError] = useState(null);
    const [processing, setProcessing] = useState(false);
    const [disabled, setDisabled] = useState(true);
    const [clientSecret, setClientSecret] = useState('');
    const stripe = useStripe();
    const elements = useElements();

    useEffect(() => {
        axios.post("http://localhost/api/stripe", {items: [{id: "xl-tshirt"}]}).then(resp => {
            setClientSecret(resp.data)
        })

    }, []);

    const handleChange = async (event) => {

        setDisabled(event.empty);
        setError(event.error ? event.error.message : "");
    };

    const handleSubmit = async (ev) => {
        ev.preventDefault();
        setProcessing(true);

        const payload = await stripe.confirmCardPayment(clientSecret, {
            payment_method: {
                card: elements.getElement(CardElement),
                billing_details: {
                    name: ev.target.name.value
                }
            }
        });

        if (payload.error) {
            setError(`Payment failed ${payload.error.message}`);
            setProcessing(false);
        } else {
            setError(null);
            setProcessing(false);
            setSucceeded(true);
        }


    };
    return (
        <form
            id="payment-form"
            onSubmit={handleSubmit}>
            <CardElement id="card-element"
                         className="card-style"
                         onChange={handleChange}
                         options={{hidePostalCode: true}}
            />
            <button
                disabled={processing || disabled || succeeded}
                id="submit"
            >
        <span id="button-text">
          {processing ? (
              <div className="spinner" id="spinner"/>
          ) : (
              "Pay"
          )}
        </span>
            </button>
            {/* Show any error that happens when processing the payment */}
            {error && (
                <div className="card-error" role="alert">
                    {error}
                </div>
            )}
            {/* Show a success message upon completion */}
            <p className={succeeded ? "result-message" : "result-message hidden"}>
                Payment succeeded, see the result in your
                <a
                    href={`https://dashboard.stripe.com/test/payments`}
                >
                    {" "}
                    Stripe dashboard.
                </a> Refresh the page to pay again.
            </p>
        </form>
    );
};

export default CardForm