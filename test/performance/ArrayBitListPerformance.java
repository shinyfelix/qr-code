package performance;

import org.junit.jupiter.api.Assertions;
import testutil.TestUtil;
import utils.ArrayBitList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.ToLongFunction;

public class ArrayBitListPerformance {
    public static void main(String[] args) {
        List<Result> arrayBitList=new ArrayList<>();
        List<Result> arrayList=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayBitList.add(testArrayBitList());
            arrayBitList.add(testArrayBitList());
            arrayList.add(testArrayList());
            arrayList.add(testArrayList());
            arrayList.add(testArrayList());
            arrayBitList.add(testArrayBitList());
        }
        double writeBit=calculateAvg(arrayBitList,Result::timeWrite);
        double writeArray=calculateAvg(arrayList,Result::timeWrite);
        double readBit=calculateAvg(arrayBitList,Result::timeRead);
        double readArray=calculateAvg(arrayList,Result::timeRead);
        double memoryBit=calculateAvg(arrayBitList,Result::memory);
        double memoryArray=calculateAvg(arrayList,Result::memory);
        System.out.println("ArrayBitList: writing time: "+writeBit+"ms per element, reading time: "+readBit+"ms per element, memory: "+memoryBit+"B per element");
        System.out.println("ArrayList: writing time: "+writeArray+"ms per element, reading time: "+readArray+"ms per element, memory: "+memoryArray+"B per element");
    }

    public static double calculateAvg(List<Result> list, ToLongFunction<? super Result> mapper){
        OptionalDouble optional=list.stream().mapToLong(mapper).average();
        Assertions.assertTrue(optional.isPresent());
        return optional.getAsDouble()/100_000_000;
    }
    public static long getMemory(){
        var memory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        return memory;
    }
    public record Result(long memory,long timeWrite,long timeRead){}
    public static Result testArrayList(){
        return test(new Wrapper() {
            ArrayList<Boolean> arrayList=new ArrayList<>();
            @Override
            public void append(boolean bit) {
                arrayList.add(bit);
            }

            @Override
            public boolean read(int position) {
                return arrayList.get(position);
            }

            @Override
            public void clear() {
                arrayList.clear();
            }
        });
    }
    public static Result testArrayBitList(){
        return test(new Wrapper() {
            ArrayBitList arrayBitList=new ArrayBitList();
            @Override
            public void append(boolean bit) {
                arrayBitList.append(bit);
            }

            @Override
            public boolean read(int position) {
                return TestUtil.assertAndGet(arrayBitList.readBit(position));
            }

            @Override
            public void clear() {
                arrayBitList.clear();
            }
        });
    }
    public static Result test(Wrapper wrapper){
        long time=System.currentTimeMillis();
        for (int i = 0; i < 100_000_000; i++) {
            wrapper.append(i%2==0);
        }
        long timeWrite=System.currentTimeMillis()-time;
        time=System.currentTimeMillis();
        for (int i = 0; i < 100_000_000; i++) {
            Assertions.assertEquals(i%2==0,wrapper.read(i));
        }
        var result= new  Result(getMemory(),timeWrite,System.currentTimeMillis()-time);
        wrapper.clear();
        return result;
    }
    public interface Wrapper{
        void append(boolean bit);
        boolean read(int position);
        void clear();
    }
}
