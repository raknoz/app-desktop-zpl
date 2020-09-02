package demo_zpl.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageConverterService {

    //private int blackLimit = 380;
    //private int total;
    //private int widthBytes;
    private static final char[] AUX_BINARY_CHAR = {'0', '0', '0', '0', '0', '0', '0', '0'};
    private static final Map<Integer, String> MAP_CODE = new HashMap<>();

    {
        MAP_CODE.put(1, "G");
        MAP_CODE.put(2, "H");
        MAP_CODE.put(3, "I");
        MAP_CODE.put(4, "J");
        MAP_CODE.put(5, "K");
        MAP_CODE.put(6, "L");
        MAP_CODE.put(7, "M");
        MAP_CODE.put(8, "N");
        MAP_CODE.put(9, "O");
        MAP_CODE.put(10, "P");
        MAP_CODE.put(11, "Q");
        MAP_CODE.put(12, "R");
        MAP_CODE.put(13, "S");
        MAP_CODE.put(14, "T");
        MAP_CODE.put(15, "U");
        MAP_CODE.put(16, "V");
        MAP_CODE.put(17, "W");
        MAP_CODE.put(18, "X");
        MAP_CODE.put(19, "Y");
        MAP_CODE.put(20, "g");
        MAP_CODE.put(40, "h");
        MAP_CODE.put(60, "i");
        MAP_CODE.put(80, "j");
        MAP_CODE.put(100, "k");
        MAP_CODE.put(120, "l");
        MAP_CODE.put(140, "m");
        MAP_CODE.put(160, "n");
        MAP_CODE.put(180, "o");
        MAP_CODE.put(200, "p");
        MAP_CODE.put(220, "q");
        MAP_CODE.put(240, "r");
        MAP_CODE.put(260, "s");
        MAP_CODE.put(280, "t");
        MAP_CODE.put(300, "u");
        MAP_CODE.put(320, "v");
        MAP_CODE.put(340, "w");
        MAP_CODE.put(360, "x");
        MAP_CODE.put(380, "y");
        MAP_CODE.put(400, "z");
    }

    private String convertImgageToZpl(BufferedImage image, final int blacknessLimitPercentage) {
        final ImageZpl imageZpl = processImage(image, blacknessLimitPercentage);

        return String.format("^XA \n^POI \n^LH0,0 \n^PW804 \n^LL%d "
                + "\n^FO45,25^GFA, %d, %d, %d, %s ^FS \n^XZ",
                image.getHeight() < 200? 200 : image.getHeight() + 10,
                imageZpl.getBinaryByteCount(),
                imageZpl.getGraphicFieldCount(),
                imageZpl.getBytesPerRow(),
                imageZpl.getData()
        );
    }

    private ImageZpl processImage(final BufferedImage originalImage, final int blacknessLimitPercentage) {
        final StringBuilder sb = new StringBuilder();
        final Graphics2D graphics = originalImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
        final int blackLimit = (blacknessLimitPercentage * 768 / 100);
        final int height = originalImage.getHeight();
        final int width = originalImage.getWidth();
        int rgb, red, green, blue, index = 0;
        char[] auxBinaryChar = AUX_BINARY_CHAR;
        int bytesPerRow = width / 8;
        if (width % 8 > 0) {
            bytesPerRow += 1;
        }
        int totalBytes = bytesPerRow * height;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                rgb = originalImage.getRGB(w, h);
                red = (rgb >> 16) & 0x000000FF;
                green = (rgb >> 8) & 0x000000FF;
                blue = (rgb) & 0x000000FF;
                char auxChar = '1';
                int totalColor = red + green + blue;
                if (totalColor > blackLimit) {
                    auxChar = '0';
                }
                auxBinaryChar[index] = auxChar;
                index++;
                if (index == 8 || w == (width - 1)) {
                    sb.append(fourByteBinary(new String(auxBinaryChar)));
                    auxBinaryChar = AUX_BINARY_CHAR;
                    index = 0;
                }
            }
            sb.append("\n");
        }
        return new ImageZpl(totalBytes, totalBytes, bytesPerRow, encodeHexAscii(sb.toString(), bytesPerRow));
    }

    private String fourByteBinary(String binaryStr) {
        int decimal = Integer.parseInt(binaryStr, 2);
        if (decimal > 15) {
            return Integer.toString(decimal, 16).toUpperCase();
        } else {
            return "0" + Integer.toString(decimal, 16).toUpperCase();
        }
    }

    private String encodeHexAscii(final String code, final int widthBytes) {
        int maxLine = widthBytes * 2;
        StringBuilder sbCode = new StringBuilder();
        StringBuilder sbLinea = new StringBuilder();
        String previousLine = null;
        int counter = 1;
        char aux = code.charAt(0);
        boolean firstChar = false;
        for (int i = 1; i < code.length(); i++) {
            if (firstChar) {
                aux = code.charAt(i);
                firstChar = false;
                continue;
            }
            if (code.charAt(i) == '\n') {
                if (counter >= maxLine && aux == '0') {
                    sbLinea.append(",");
                } else if (counter >= maxLine && aux == 'F') {
                    sbLinea.append("!");
                } else if (counter > 20) {
                    int module20 = (counter % 20);
                    sbLinea.append(MAP_CODE.get((counter / 20) * 20));
                    if (module20 != 0) {
                        sbLinea.append(MAP_CODE.get(module20));
                    }
                    sbLinea.append(aux);
                } else {
                    sbLinea.append(MAP_CODE.get(counter)).append(aux);
                    MAP_CODE.get(counter);
                }
                counter = 1;
                firstChar = true;
                if (sbLinea.toString().equals(previousLine)) {
                    sbCode.append(":");
                } else {
                    sbCode.append(sbLinea.toString());
                }
                previousLine = sbLinea.toString();
                sbLinea.setLength(0);
                continue;
            }
            if (aux == code.charAt(i)) {
                counter++;
            } else {
                if (counter > 20) {
                    int module20 = (counter % 20);
                    sbLinea.append(MAP_CODE.get((counter / 20) * 20));
                    if (module20 != 0) {
                        sbLinea.append(MAP_CODE.get(module20));
                    }
                    sbLinea.append(aux);
                } else {
                    sbLinea.append(MAP_CODE.get(counter)).append(aux);
                }
                counter = 1;
                aux = code.charAt(i);
            }
        }
        return sbCode.toString();
    }

    private static class ImageZpl {

        int binaryByteCount;
        int graphicFieldCount;
        int bytesPerRow;
        String data;

        private ImageZpl() {
        }

        /**
         * Format: ^GFa,b,c,d,data (a = ANSSI codec).
         *
         * @param binaryByteCount binary byte count.
         * @param graphicFieldCount graphic field count.
         * @param bytesPerRow bytes per row.
         * @param data data.
         */
        public ImageZpl(final int binaryByteCount, final int graphicFieldCount, final int bytesPerRow, final String data) {
            this.binaryByteCount = binaryByteCount;
            this.graphicFieldCount = graphicFieldCount;
            this.bytesPerRow = bytesPerRow;
            this.data = data;
        }

        public int getBinaryByteCount() {
            return binaryByteCount;
        }

        public int getGraphicFieldCount() {
            return graphicFieldCount;
        }

        public int getBytesPerRow() {
            return bytesPerRow;
        }

        public String getData() {
            return data;
        }
    }

    private BufferedImage convertCMYK2RGB(BufferedImage image) throws IOException {
        //Create a new RGB image
        BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        // then do a funky color convert
        ColorConvertOp op = new ColorConvertOp(null);
        op.filter(image, rgbImage);
        return rgbImage;
    }

    public static String main(final String filePath) throws IOException {
        //int scaledWidth = 700;
        //int scaledHeight = 768;
        //final BufferedImage originalImage = ImageIO.read(ImageResizer.resize(filePath, scaledWidth, scaledHeight));
        BufferedImage originalImage = ImageIO.read(new File(filePath));
        final ImageConverterService zp = new ImageConverterService();
        return zp.convertImgageToZpl(originalImage, 50);
    }
}
