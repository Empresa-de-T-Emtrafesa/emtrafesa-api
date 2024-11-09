package com.emtrafesa.service;

import com.emtrafesa.dto.PaymentCaptureResponse;
import com.emtrafesa.dto.PaymentOrderResponse;
import jakarta.mail.MessagingException;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl)throws MessagingException;

    PaymentCaptureResponse capturePayment(String orderId) throws MessagingException;
}
