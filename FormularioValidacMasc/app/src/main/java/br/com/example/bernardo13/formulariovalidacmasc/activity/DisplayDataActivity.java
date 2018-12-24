package br.com.example.bernardo13.formulariovalidacmasc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.example.bernardo13.formulariovalidacmasc.R;

public class DisplayDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.text_name);
        name.setText("Nome: " + intent.getStringExtra(MainActivity.EXTRA_NAME));
        TextView phone = (TextView) findViewById(R.id.text_email);
        phone.setText("E-mail: " + intent.getStringExtra(MainActivity.EXTRA_EMAIL));
        TextView email = (TextView) findViewById(R.id.text_phone);
        email.setText("Telefone: " + intent.getStringExtra(MainActivity.EXTRA_PHONE));
        TextView password = (TextView) findViewById(R.id.text_password);
        password.setText("Senha: " + intent.getStringExtra(MainActivity.EXTRA_PASSWORD));
        TextView creditCardNumber = (TextView) findViewById(R.id.text_credit_card_number);
        creditCardNumber.setText("Número: " + intent.getStringExtra(MainActivity.EXTRA_CREDIT_CARD_NUMBER));
        TextView creditCardExpiry = (TextView) findViewById(R.id.text_credit_card_expiry);
        creditCardExpiry.setText("Expiração: " + intent.getStringExtra(MainActivity.EXTRA_CREDIT_CARD_EXPIRY));
        TextView creditCardCvc = (TextView) findViewById(R.id.text_credit_card_cvc);
        creditCardCvc.setText("CVC: " + intent.getStringExtra(MainActivity.EXTRA_CREDIT_CARD_CVC));
        TextView agreement = (TextView) findViewById(R.id.text_agreement);
        agreement.setText("Termo de serviço: " + intent.getStringExtra(MainActivity.EXTRA_AGREEMENT));
    }
}
