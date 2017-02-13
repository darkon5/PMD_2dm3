package moe.gimme.calculatronix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainCalcu extends AppCompatActivity {

    private TextView numSmall;
    private TextView numBig;

    String operando;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonMas;
    private Button buttonDiv;
    private Button buttonMenos;
    private Button buttonMult;
    private Button buttonC;
    private Button buttonIgual;

    public int resultado=0;
    public int numero = 0;
    public int valorAnterior;
    public int modoOperacion;
    public int xmulti;
    //public char[] numCharArray;
    int posicionChar = 0;

    //int numMostrar = (int) numero.charAt(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calcu);


        numBig = (TextView) findViewById(R.id.numBig);
        numSmall = (TextView) findViewById(R.id.numSmall);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonC = (Button) findViewById(R.id.buttonClear);
        buttonIgual = (Button) findViewById(R.id.buttonIgual);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonMult = (Button) findViewById(R.id.buttonMult);
        buttonMas = (Button) findViewById(R.id.buttonMas);
        buttonMenos = (Button) findViewById(R.id.buttonMenos);

        actualizarNumero();

    }

    public void actualizarNumero(){

        switch (modoOperacion){
            case 1:
                operando = "+";
                break;
            case 2:
                operando = "-";
                break;
            case 3:
                operando = "*";
                break;
            case 4:
                operando = "/";
                break;
            case 0:
                operando = "";
        }

        if (valorAnterior!=0){
        numSmall.setText(valorAnterior + " " + operando);
        }else{
            numSmall.setText("");
        }

        //String showNumero = (String) numero;

        numBig.setText("" + numero);
    }

    public void printNum(int nmbr){
        if (numero==0){
            numero=0;
        }
        numero=numero*10;
        numero=numero+(nmbr);
        actualizarNumero();
    }

    public void botonC(View vista){
        numero=0;
        actualizarNumero();
    }
    public void boton0(View vista){
        printNum(0);
    }
    public void boton1(View vista){
        printNum(1);
    }
    public void boton2(View vista){
        printNum(2);
    }
    public void boton3(View vista){
        printNum(3);
    }
    public void boton4(View vista){
        printNum(4);
    }
    public void boton5(View vista){
        printNum(5);
    }
    public void boton6(View vista){
        printNum(6);
    }
    public void boton7(View vista){
        printNum(7);
    }
    public void boton8(View vista){
        printNum(8);
    }
    public void boton9(View vista){
        printNum(9);
    }


    public void botonMas(View vista){
        valorAnterior=numero;
        modoOperacion = 1;
        numero=0;
        actualizarNumero();
    }
    public void botonMenos(View vista){
        valorAnterior=numero;
        modoOperacion = 2;
        numero=0;
        actualizarNumero();
    }
    public void botonMult(View vista){
        valorAnterior=numero;
        modoOperacion = 3;
        numero=0;
        actualizarNumero();
    }
    public void botonDiv(View vista){
        valorAnterior=numero;
        modoOperacion = 4;
        numero=0;
        actualizarNumero();
    }

    public void botonIgual(View vista){

        int n1;
        //char[] numero1 = valorAnterior.toCharArray();
        //int distancia = valorAnterior.length();

        //n1 = valorAnterior.chars();

        //n1 = (int) asdada;

        //for (int i = 0; i < distancia; i++) {
        //n1 = numero1[i];
        //}

        switch (modoOperacion){
            case 1://suma
                resultado = valorAnterior + numero;
                break;
            case 2://resta
                resultado = valorAnterior - numero;
                break;
            case 3://mult
                resultado = valorAnterior * numero;
                break;
            case 4://div
                if (numero==0){
                    Toast.makeText(getBaseContext(),"ERROR: No se puede dividir entre cero, loco.",Toast.LENGTH_SHORT).show();
                    numero=1;
                }
                resultado = valorAnterior / numero;
                break;
        }

        numero=resultado;

        modoOperacion=0;
        valorAnterior=0;

        //numSmall.setText("" + resultado);


        actualizarNumero();
    }
}
