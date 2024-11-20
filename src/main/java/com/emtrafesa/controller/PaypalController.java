package com.emtrafesa.controller;

import com.emtrafesa.service.PaypalService;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.webjars.NotFoundException;

import java.io.IOException;

@RestController
@RequestMapping("/paypal")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    @PostMapping("/create-order")
    public String  createOrder(@RequestParam double totalAmount) {
        //String returnUrl = "https://govench-api.onrender.com/api/v1/admin/payments/payment";
        String returnUrl = "http://localhost:8080/api/v2/paypal/payment";
        String cancelUrl = "https://blog.fluidui.com/top-404-error-page-examples/";
        try {
            String orderId = paypalService.createOrder(totalAmount, returnUrl, cancelUrl);
            if (orderId == null) {
                //return new RedirectView("/error?status=error");
            }

            String approvalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=" + orderId;
            return approvalUrl;
            //return new RedirectView(approvalUrl);

        } catch (IOException e) {
            e.printStackTrace();
            //return new RedirectView("/error?status=error");
            return "/error?status=error";
        }
    }

    @GetMapping("/payment")
    public RedirectView handlePayment(@RequestParam String token) {
        try {
            HttpResponse<Order> response = paypalService.captureOrder(token);

            if (response.statusCode() == 201) { // Pago exitoso

                try {
                    //a

                    RedirectView redirectView = new RedirectView("https://www.youtube.com/watch?v=dQw4w9WgXcQ"); // Ruta de la página de éxito
                    redirectView.addStaticAttribute("message", "Pago completado con éxito e inscripción realizada.");
                    return redirectView;
                } catch (NotFoundException e) {
                    return new RedirectView("/error?message=" + e.getMessage()); // Redirige con error
                } catch (IllegalArgumentException e) {
                    return new RedirectView("/error?message=" + e.getMessage()); // Redirige con error
                }
            } else {
                return new RedirectView("/error?message=El pago fue cancelado o fallido.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new RedirectView("/error?message=Ocurrió un error durante el proceso de pago.");
        }
    }
}
