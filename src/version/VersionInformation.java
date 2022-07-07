package version;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VersionInformation {
    private final int size;
    private final int version;
    private VersionInformation(int size,int version){
        this.size=size;
        this.version=version;
    }
    public List<Integer> getAlignmentPatterns(){
        return switch (version){
            case 2->List.of(6,18);
            case 3->List.of(6,22);
            case 4->List.of(6,26);
            case 5->List.of(6,30);
            case 6->List.of(6,34);
            case 7->List.of(6,22,38);
            case 8->List.of(6,24,42);
            case 9->List.of(6,26,46);
            case 10->List.of(6,28,50);
            case 11->List.of(6,30,54);
            case 12->List.of(6,32,58);
            case 13->List.of(6,34,62);
            case 14->List.of(6,26,46,66);
            case 15->List.of(6,26,48,70);
            case 16->List.of(6,26,50,74);
            case 17->List.of(6,30,54,78);
            case 18->List.of(6,30,56,82);
            case 19->List.of(6,30,58,86);
            case 20->List.of(6,34,62,90);
            case 21->List.of(6,28,50,72,94);
            case 22->List.of(6,26,50,74,98);
            case 23->List.of(6,30,54,78,102);
            case 24->List.of(6,28,54,80,106);
            case 25->List.of(6,32,58,84,110);
            case 26->List.of(6,30,58,86,114);
            case 27->List.of(6,34,62,90,118);
            case 28->List.of(6,26,50,74,98,122);
            case 29->List.of(6,30,54,78,102,126);
            case 30->List.of(6,26,52,78,104,130);
            case 31->List.of(6,30,56,82,108,134);
            case 32->List.of(6,34,60,86,112,138);
            case 33->List.of(6,30,58,86,114,142);
            case 34->List.of(6,34,62,90,118,146);
            case 35->List.of(6,40,54,78,102,126,150);
            case 36->List.of(6,24,50,76,102,128,154);
            case 37->List.of(6,28,54,80,106,132,158);
            case 38->List.of(6,32,58,84,110,136,162);
            case 39->List.of(6,26,54,82,110,138,166);
            case 40->List.of(6,30,58,86,114,142,170);
            default -> Collections.emptyList();
        };
    }
    public int getSize(){
        return size;
    }
    public int getVersion(){
        return version;
    }
    public static Optional<VersionInformation> ofSize(int size){
        if ((size-21)%4!=0)return Optional.empty();
        int version=(size-21)/4;
        if (version<1||version>40)return Optional.empty();
        return Optional.of(new VersionInformation(size,version));
    }
    public static Optional<VersionInformation> ofVersion(int version){
        if (version<1||version>40)return Optional.empty();
        return Optional.of(new VersionInformation(version*4+21,version));
    }
}
