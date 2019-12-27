# Leitor de QR Code e Bar Code

### Introdução

---

O Leitor de QR Code é um aplicativo android feito para ler  e gerar QR Codes e Bar Codes. Esse aplicativo foi construído durante uma aula de Desenvolvimento para Android II promovido pela Pós Graduação em Dispositivos Móveis na Universidade Potiguar.

### Funcionamento

---

Ao executar a aplicação, o usuário é apresentado à tela principal do leitor de QR Code. A tela possui um botão que inicia o scan do QR code ou do Bar Code:

<p align="center">
  <img src="./assets/Tela principal do qr code.png"><br/>
  Tela Principal
</p>

### Escaneando o QR e Bar Code

---

Na tela de scan, ele abre como se fosse um Bar code scan, mas, se enquadrar o QR Code dentro, ele irá ler sem problemas.

<p align="center">
  <img src="./assets/tela de leitura do qr code.png"><br/>
  Tela de Scan
</p>

### Tela de resultado

---

Depois que o QR/Bar Code é lido, ele irá mostra a mensagem ou codigo e irá executar a seguinte função:

```
public void GeradorQRCode() throws WriterException {

        String texto = txtCodeLido.getText().toString();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(texto, BarcodeFormat.QR_CODE, 300, 300); // escolho o qrCode
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap imagem = barcodeEncoder.createBitmap(bitMatrix);
            imgCode.setImageBitmap(imagem);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
    
```
essa função irá pegar o texto ou numeração que foi lida e irá gerar um novo QR/Bar code com a mesma informação e mostrará em tela para que outro dispositivo possa ler:

<p align="center">
  <img src="./assets/WhatsApp Image 2019-12-27 at 13.21.54.jpeg"><br/>
  Tela de resultado
</p>

