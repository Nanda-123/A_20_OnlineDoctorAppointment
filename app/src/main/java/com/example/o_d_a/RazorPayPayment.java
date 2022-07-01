package com.example.o_d_a;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONException;
import org.json.JSONObject;

public class RazorPayPayment extends AppCompatActivity implements PaymentResultListener {

    private Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_pay_payment);

        btPay = findViewById(R.id.bt_pay);

        String sAmount = "1";

        int amount= Math.round(Float.parseFloat(sAmount)*100);

        btPay.setOnClickListener(view -> {
            Checkout checkout = new Checkout();
            checkout.setKeyID("rzp_test_c5bh19emd7Yxpm");
            checkout.setImage(R.drawable.doc);

            JSONObject object = new JSONObject();
            try{
                object.put("name", "Online Doctor Appointment");
                object.put("description", "Test Payment");
                //object.put("send_sms_hash", true);
                //object.put("allow_rotation", false);
                // options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                //  options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                object.put("theme.color", "#0093DD");
                //You can omit the image option to fetch the image from dashboard
                object.put("currency", "INR");
                object.put("amount", amount);
                object.put("prefill.contact", "9620009713");
                object.put("prefill.email", "talawaryashoda@gmail.com");
                checkout.open(RazorPayPayment.this, object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();
    }
    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,
                Toast.LENGTH_SHORT).show();
    }
}