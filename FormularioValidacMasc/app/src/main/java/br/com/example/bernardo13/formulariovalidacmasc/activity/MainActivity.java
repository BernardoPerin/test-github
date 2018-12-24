package br.com.example.bernardo13.formulariovalidacmasc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.redmadrobot.inputmask.MaskedTextChangedListener;

import java.util.Iterator;
import java.util.List;

import mask.InputMask;
import br.com.example.bernardo13.formulariovalidacmasc.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener {

    public static final String EXTRA_NAME = "br.com.example.bernardo13.formulariovalidacmasc.NAME";
    public static final String EXTRA_EMAIL = "br.com.example.bernardo13.formulariovalidacmasc.EMAIL";
    public static final String EXTRA_PHONE = "br.com.example.bernardo13.formulariovalidacmasc.PHONE";
    public static final String EXTRA_PASSWORD = "br.com.example.bernardo13.formulariovalidacmasc.PASSWORD";
    public static final String EXTRA_CREDIT_CARD_NUMBER = "br.com.example.bernardo13.formulariovalidacmasc.EXTRA_CREDIT_CARD_NUMBER";
    public static final String EXTRA_CREDIT_CARD_EXPIRY = "br.com.example.bernardo13.formulariovalidacmasc.EXTRA_CREDIT_CARD_EXPIRY";
    public static final String EXTRA_CREDIT_CARD_CVC = "br.com.example.bernardo13.formulariovalidacmasc.EXTRA_CREDIT_CARD_CVC";
    public static final String EXTRA_AGREEMENT = "br.com.example.bernardo13.formulariovalidacmasc.EXTRA_AGREEMENT";

    private Validator validator;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    private EditText name;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Email(messageResId = R.string.validation_error_email)
    private EditText email;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Pattern(regex = "(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.]+[0-9])", messageResId = R.string.validation_error_phone)
    private EditText phone;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS, messageResId = R.string.validation_error_password)
    private EditText password;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Length(min = 19, message = "Informe pelo menos 19 dígitos")
    private EditText creditCardNumber;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    private EditText creditCardExpiry;

    @NotEmpty(messageResId = R.string.validation_error_not_empty)
    @Length(min = 3, message = "Informe pelo menos 3 dígitos")
    private EditText creditCardCvc;

    @Checked(message = "Você precisa aceitar os termos de serviço")
    private CheckBox agreement;

    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validator = new Validator(this);
        validator.setValidationListener(this);

        name = (EditText) findViewById(R.id.edit_name);
        name.getText().toString();
        email = (EditText) findViewById(R.id.edit_email);
        phone = (EditText) findViewById(R.id.edit_phone);
        password = (EditText) findViewById(R.id.edit_password);
        creditCardExpiry = (EditText) findViewById(R.id.edit_credit_card_expiry);
        creditCardNumber = (EditText) findViewById(R.id.edit_credit_card_number);
        creditCardCvc = (EditText) findViewById(R.id.edit_credit_card_cvc);
        agreement = (CheckBox) findViewById(R.id.check_agreement);

        MaskedTextChangedListener phoneNumberMask = InputMask.getPhoneMask(phone);
        phone.addTextChangedListener(phoneNumberMask);
        phone.setOnFocusChangeListener(phoneNumberMask);
        phone.setHint(phoneNumberMask.placeholder());

        MaskedTextChangedListener dateMask = InputMask.getDateMask(creditCardExpiry);
        creditCardExpiry.addTextChangedListener(dateMask);
        creditCardExpiry.setOnFocusChangeListener(dateMask);
        creditCardExpiry.setHint(dateMask.placeholder());

        MaskedTextChangedListener creditCardNumberMask = InputMask.getCreditCardNumberMask(creditCardNumber);
        creditCardNumber.addTextChangedListener(creditCardNumberMask);
        creditCardNumber.setOnFocusChangeListener(creditCardNumberMask);
        creditCardNumber.setHint(creditCardNumberMask.placeholder());

        send = (Button) findViewById(R.id.button_send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_send) {
            validator.validate();
        }
    }

    @Override
    public void onValidationSucceeded() {
        Intent intent = new Intent(MainActivity.this, DisplayDataActivity.class);
        intent.putExtra(EXTRA_NAME, name.getText().toString());
        intent.putExtra(EXTRA_EMAIL, email.getText().toString());
        intent.putExtra(EXTRA_PHONE, phone.getText().toString());
        intent.putExtra(EXTRA_PASSWORD, password.getText().toString());
        intent.putExtra(EXTRA_CREDIT_CARD_NUMBER, creditCardNumber.getText().toString());
        intent.putExtra(EXTRA_CREDIT_CARD_EXPIRY, creditCardExpiry.getText().toString());
        intent.putExtra(EXTRA_CREDIT_CARD_CVC, creditCardCvc.getText().toString());
        intent.putExtra(EXTRA_AGREEMENT, (agreement.isChecked()) ? "Termo aceito" : "Termo não aceito");
        startActivity(intent);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        Iterator<ValidationError> iterator = errors.iterator();

        while (iterator.hasNext()) {
            ValidationError error = iterator.next();

            View view = error.getView();

            if (view instanceof TextView) {
                ((TextView) view).setError(error.getCollatedErrorMessage(this));
            }

            iterator.remove();
        }
    }
}