package testutil;

import utils.EmptyQrCodeCell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static utils.EmptyQrCodeCell.*;

public class TestUtil {

    public static <T> T assertAndGet(Optional<T> optional){
        assertTrue(optional.isPresent());
        return optional.get();
    }

    public static EmptyQrCodeCell[][] readQRCode(String filename){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line=bufferedReader.readLine();
            EmptyQrCodeCell[][] emptyQrCodeCells=new EmptyQrCodeCell[line.length()][line.length()];
            for (int i = 0; i < line.length(); i++) {
                emptyQrCodeCells[i][0]=switch (line.charAt(i)){
                    case 'n'->ON;
                    case 'f'->OFF;
                    case 'p'->PRE_MARK;
                    default -> EMPTY;
                };
            }
            for (int i = 1; i <line.length() ; i++) {
                line=bufferedReader.readLine();
                for (int j = 0; j < line.length(); j++) {
                    emptyQrCodeCells[j][i]=switch (line.charAt(j)){
                        case 'n'->ON;
                        case 'f'->OFF;
                        case 'p'->PRE_MARK;
                        default -> EMPTY;
                    };
                }
            }
            return emptyQrCodeCells;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
