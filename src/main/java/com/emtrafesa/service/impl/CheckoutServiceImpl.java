package com.emtrafesa.service.impl;

import com.emtrafesa.dto.PagoDTO;
import com.emtrafesa.dto.PaymentCaptureResponse;
import com.emtrafesa.dto.PaymentOrderResponse;
import com.emtrafesa.integration.payment.paypal.dto.OrderCaptureResponse;
import com.emtrafesa.integration.payment.paypal.dto.OrderResponse;
import com.emtrafesa.integration.service.PayPalService;
import com.emtrafesa.service.CheckoutService;
import com.emtrafesa.service.PurchaseService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PayPalService payPalService;
    private final PurchaseService purchaseService;


    @Override
    public PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl) throws MessagingException {
        OrderResponse orderResponse =payPalService.createOrder(purchaseId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaymentOrderResponse(paypalUrl);
    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId) throws MessagingException{
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();
            PagoDTO pagoDTO = purchaseService.confirmPago((long) Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setPurchaseId(Math.toIntExact(pagoDTO.getId()));

            //sendPurchaseConfirmationEmail(pagoDTO);

        }
        return paypalCaptureResponse;
    }

//    private void sendPurchaseConfirmationEmail(PagoDTO pagoDTO) throws MessagingException {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("user", userEmail);
//        model.put("total", pagoDTO.getMonto());
//        model.put("orderUrl", "http://localhost:4200/order/" + pagoDTO.getId());
//
//        Mail mail = emailService.createMail(
//                userEmail,
//                "Confirmación de Compra",
//                model,
//                mailFrom
//        );
//        emailService.sendEmail(mail,"email/purchase-confirmation-template");
//    }
}
