import java.util.*;

/**
 * Created by rahulsehrawat on 9/10/17.
 */
public class decompress {

    public static void main(String[] args) {

        System.out.println(superUgly(new int[]{2,3,5},20));

    }

    public static ArrayList<Integer> superUgly(int[] primes,int k)
    {
        Arrays.sort(primes);
        int pLen = primes.length;

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(1);

        PriorityQueue<pair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.value));
        HashSet<Integer> hashSet = new HashSet<>();

        int next_ugly_number;
        int[] indices = new int[pLen];

        for(int i=0;i<pLen;i++) {
            hashSet.add(primes[i]);
            priorityQueue.add(new pair(i,primes[i]));
        }
        while(ans.size()!=k+1)
        {
            pair pair = priorityQueue.poll();
            next_ugly_number = pair.value;
            ans.add(next_ugly_number);
            indices[pair.index]+=1;

            int temp = ans.get(indices[pair.index])*primes[pair.index];
            if (!hashSet.contains(temp))
            {
                priorityQueue.add(new pair(pair.index,temp));
                hashSet.add(temp);
            }
            else {
                while(hashSet.contains(temp))
                {
                    indices[pair.index]+=1;
                     temp = ans.get(indices[pair.index])*primes[pair.index];

                }
                priorityQueue.add(new pair(pair.index,temp));
                hashSet.add(temp);

            }

        }


        ans.remove(0);
        return ans;

    }




}
class pair
{
    int index,value;
    public pair(int i,int v)
    {
        index = i;
        value = v;
    }
}
