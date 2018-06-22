import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhaojie on 18-3-29.
 */
public class Calcate {
    /**
     * 1.KMP算法
     * @param pattern
     * @return
     */
    int[] calc_max_length(String pattern){
        int[] max_lengths=new int[pattern.length()];
        int max_length=0;
        for (int i=1;i<pattern.length();i++){
            while(max_length>0 && pattern.charAt(max_length) != pattern.charAt(i)){
                max_length = max_lengths[max_length - 1];
            }
            if(pattern.charAt(max_length)==pattern.charAt(i)){
                max_length++;
            }
            max_lengths[i]=max_length;
        }
        return  max_lengths;
    }
    int kmp(String a,String b){
        int[] c= calc_max_length(b);
        int xiangdeng=0;
        for(int i=0;i<a.length();i++){
            if(xiangdeng ==b.length()){
             return --i;
            }
            if(xiangdeng>0 && a.charAt(i) !=b.charAt(xiangdeng)){
                System.out.println("xiangdeng--ttt-"+xiangdeng);
                xiangdeng=c[xiangdeng-1];
                System.out.println("xiangdeng---"+xiangdeng);
            }
            if(a.charAt(i)==b.charAt(xiangdeng)){
                xiangdeng++;
            }
        }
        return -1;
    }

    //2.在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    // 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    int containsNum(int[][] tmp,int num){
        int a=0;//行下标
        int b=tmp[0].length-1;//列下标
        while(a<tmp.length || b>0) {
            while (a < tmp.length && tmp[a][b] < num) {
                a++;
            }
            if (a == tmp.length) {
                return -1;
            }
            while (b > 0 && tmp[a][b] > num) {
                b--;
            }
            if (tmp[a][b] == num) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 3.根据前序遍历和中序遍历重建二叉树
     */
    class Node{
        int value=0;
        public Node left=null;
        public Node right=null;
        Node(int value){
            this.value=value;
        }

    }
    Node createTree(int[] pre,int[] mid,int pre_num){
        Node root=null;
        int mid_num=0;
        boolean flag=false;
        for(int i=pre_num;i<pre.length;i++){
            for(int j=0;j<mid.length;j++){
                if(pre[i]==mid[j]){
                    root=new Node(pre[i]);
                    mid_num=j;
                    pre_num=i;
                    flag=true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }
        if(root !=null){
            System.out.println("root----"+root.value);
            root.left=createTree(pre, Arrays.copyOfRange(mid,0,mid_num),pre_num);
            root.right=createTree(pre,Arrays.copyOfRange(mid,mid_num+1,mid.length),pre_num);
        }
        return root;
    }
    //用前序遍历验证
    void printTree(Node root){
        if(root !=null) {
            System.out.print("---" + root.value);
            printTree(root.left);
            printTree(root.right);
        }
    }

    /**
     * 4.用栈实现队列
     * @param <T>
     */
    class Myqueue <T>{
        private Stack<T> in=new Stack<T>();
        private Stack<T> out=new Stack<T>();
        public void push(T t){
            in.push(t);
        }
        public T pop(){
            if(out.isEmpty()){
                while (!in.isEmpty()){
                    out.push(in.pop());
                }
            }
           return out.pop();
        }
    }

    /**
     * 5.斐波那契数列非递归实现
     * @param n
     * @return
     */
    int fbnq(int n){
        int a=1,b=2,sum=0;
        if(n==1){
            return a;
        }else if(n==2){
            return b;
        }else{
            for(int i=3;i<=n;i++){
                sum=a+b;
                a=b;
                b=sum;
            }
            return sum;
        }
    }

    /**
     * 6.把一个数组中的奇数都放到偶数的前面,并且保证奇数和偶数的相对位置不变
     * @param a
     * 思路,把奇数往前移好移,每次移动后第i位需要移动的位奇数前面数据格式必为N个奇数+N个偶数
     */
    void changeAb(int[] a){
        for(int i=0;i<a.length;i++){
            if(a[i]%2==1){
                int n=i;
                while (n>0){
                    if(a[n-1]%2==0){
                        int tmp=a[n];
                        a[n]=a[n-1];
                        a[n-1]=tmp;
                    }else{
                        break;
                    }
                    --n;
                }
            }
        }
    }

    /**
     * 7.把两个递增链表合并,不能破坏递增规则
     */
    class ListNode{
        public int value;
        public ListNode next;
        ListNode(){}
    }
//递归实现
    ListNode mergeListNode(ListNode node1,ListNode node2){
        if(node1 == null){
            return  node2;
        }
        if(node2 ==null){
            return node1;
        }
        ListNode newNode;
        if(node1.value < node2.value){
            newNode=node1;
            newNode.next=mergeListNode(node1.next,node2);
        }else{
            newNode=node2;
            newNode.next=mergeListNode(node1,node2.next);
        }
        return newNode;
    }
//非递归实现
    ListNode mergeListNode2(ListNode node1,ListNode node2){
        ListNode result=null;
        if(node1 == null){
            return node2;
        }
        if(node2 ==null){
            return node1;
        }
        if(node1.value<node2.value){
            result=node1;
            node1=node1.next;
        }else{
            result=node2;
            node2=node2.next;
        }
        while (node1 !=null && node2 !=null) {
            if(node1.value<node2.value){
                result.next=node1;
                node1=node1.next;
            }else{
                result.next=node2;
                node2=node2.next;
            }
        }
        if(node1 ==null && node2 !=null){
            result.next=node2;
        }
        if(node1 !=null && node2 ==null){
            result.next=node1;
        }
        return result;
    }

    /**
     * 8.判断parent树是否包含子结构son树,两棵树都不能为空
     * @param parent
     * @param son
     * @return
     * 思路:分两种情况判断,son树和parent树根结构相同,son树与parent树的子结构相同
     */
    boolean containsTree(Node parent,Node son){
        boolean result=false;
       if(parent.value==son.value){
           result=nodeEquels(parent,son);
       }
        if(!result){
            result=containsTree(parent.left,son);
        }
        if(!result){
            result=containsTree(parent.right,son);
        }
        return result;
    }
    boolean nodeEquels(Node a,Node b){
        if((a==null && b!=null) || (b==null && a!=null)){
            return false;
        }
        if(a==null && b==null){
            return true;
        }
        if(a.value ==b.value){
            return nodeEquels(a.left,b.left) && nodeEquels(a.right,b.right);
        }else{
            return false;
        }

    }

    /**
     * 9.顺时针打印矩阵
     * @param a
     */
    void printJuZhen(int[][] a){
        int n=a.length-1;//行下标
        int m=a[0].length-1;//列下标
        int nStart=0;
        int mStart=0;
        while (nStart<=n && mStart<=m) {
            for (int i = mStart; i <= m; i++) {
                System.out.print("--" + a[nStart][i]);
            }
            nStart++;
            for (int i = nStart; i <= n; i++) {
                System.out.print("--" + a[i][m]);
            }
            m--;
            for (int i = m ; i >= mStart; i--) {
                System.out.print("--" + a[n][i]);
            }
            n--;
            for (int i = n ; i >= nStart; i--) {
                System.out.print("--" + a[i][mStart]);
            }
            mStart++;


        }
    }

    /**
     * 10.计算二进制数中1的个数
     * @param n
     * @return
     */
    int getOneCount(int n){
        int count=0;
        while (n!=0){
                n ^= n-1;
                count++;
        }
        return count;
    }

    /**
     * 11.给定两个序列,pushV是进栈序列,popV是弹出序列,判断弹出序列是否正确
     * 例如 进栈顺序 1,2,3,4,5 出栈 4,5,3,2,1 正确. 4,5,3,1,2错误
     * @param pushV
     * @param popV
     * @return
     * 思路: 模拟进栈出栈的过程
     */
    boolean stackPopOrder(int[] pushV, int[] popV ){
        boolean result=true;
        int popLen=0;
        int pushLen=0;
        Stack<Integer> p=new Stack<Integer>();
        while ( popLen< popV.length) {
            if (pushLen > pushV.length) {
                result = false;
                break;
            }
            while (!p.isEmpty() && p.peek() == popV[popLen] ){
                p.pop();
                popLen++;
            }
            while (pushLen < pushV.length && pushV[pushLen] != popV[popLen]) {
                p.push(pushV[pushLen]);
                pushLen++;
            }
            pushLen++;//当pushV[pushLen] == popV[popLen],得从下一个判断
            popLen++;
        }
        return result;
    }

    /**
     * 12.层次遍历二叉树,借助队列实现
     * @param root
     */
    void printTreeByQueue(Node root){
        Queue<Node> queue=new LinkedList<Node>();
        if(root !=null){
            queue.add(root);
            while (!queue.isEmpty()){
                Node node= queue.poll();
                System.out.print(node.value);
                if(node.left !=null){
                    queue.add(node.left);
                }
                if(node.right !=null){
                    queue.add(node.right);
                }
            }
        }
    }

    /**
     * 13.前序遍历
     * @param root
     */
    void prePrintTree(Node root){
        Stack<Node> stack=new Stack<Node>();
        while (root !=null || !stack.isEmpty() ){
            while (root !=null){
                System.out.print(root.value);
                stack.push(root);
                root=root.left;
            }
            if(!stack.isEmpty()){
                root=stack.pop();
                root=root.right;
            }
        }
    }

    /**
     * 14.打印字符数组的全排列
     * @param a
     * @param form
     * @param to
     * 思路:从字符数组中依次取一个值作为字符串的第一个字符,然后把剩下的全排列,采用递归实现
     */
    void permutation(char[] a,int form,int to){
          if(to<=0){
              return;
          }
        if(form==to){
            System.out.print("-----------");
            System.out.println(a);
        }else{
            for(int i=form;i<=to;i++){
                swap(a,form,i);//交换产生新前缀
                permutation(a,form+1,to);
                swap(a,form,i);//将前缀换回来继续做次前缀的排列
                System.out.print("+++++++");
                System.out.println(a);
            }
        }

    }
    void swap(char[] a,int form,int to){
        char temp=a[form];
        a[form]=a[to];
        a[to]=temp;
    }

    /**
     * 15.找出数组中出现次数大于一半的数字
     * @param a
     * @return          O(n)
     * 思路   每次删除一对不相等的数字,剩余的一定是超过一半的数字
     */
    int findMoreHalf(int a[]){
        int result=0;
        int count=0;
        for(int i=0;i<a.length;i++){
            if(count ==0){
                result=a[i];
            }else{
                if(result == a[i]){
                    count++;
                }else{
                    count--;   //相当于删除一对不相等的数字
                }
            }
        }
        if(count>0){
            //校验result在数组中的次数是否超过了一半,因为如果数组中没有超过一半的元素result也可能不为0
            count=0;
            for(int num:a){
                if(result == num){
                    count++;
                }
            }
            if(count<=a.length/2){
                 result=0;
            }
        }
        return result;
    }

    /**
     * 16.找出数组中出现次数大于1/3的元素,最多有两个元素
     * @param a
     * 思路和上一个算法一样
     */
    void findMoreThree(int a[]){
         int m=0,n=0,mCount=0,nCount=0;
        for(int i=0;i<a.length;i++){
              if(mCount ==0 || m==a[i]){
                  m=a[i];
                  mCount++;
              }else if(nCount == 0 || n == a[i]){
                  n=a[i];
                  nCount++;
              }else{
                  mCount--;
                  nCount--;//相当于删除了三个不同的元素
              }

        }
        if(mCount>0){
            //校验
            mCount=0;
            for(int num:a){
                if(num == m){
                    mCount++;
                }
            }
            if(mCount>a.length/3){
                    System.out.print(m);
            }
        }
        if(nCount>0){
            nCount=0;
            for(int num:a){
                if(num == n){
                    nCount++;
                }
            }
            if(nCount>a.length/3){
                System.out.print(n);
            }
        }

    }

    /**
     * 17.求出连续子数组的最大值{1,5,-16,9,30,-20,6,8}
     * @param a
     * @return
     */
    int getMaxNumber(int[] a){
        int maxNumber=a[0];
        int currentNumber=a[0];
           for(int i=1;i<a.length;i++){
                 if(currentNumber<0){
                     currentNumber=a[i];
                 }else{
                     currentNumber+=a[i];
                 }
               if(maxNumber<currentNumber){
                   maxNumber=currentNumber;
               }
           }
        return maxNumber;
    }

    /**
     * 18.求二叉树的深度
     * @param root
     * @return
     */
    int getTreeDeep(Node root){
        int deep=0;
        if(root !=null){
            int left=getTreeDeep(root.left);
            int right=getTreeDeep(root.right);
            deep=left>right? left+1 : right+1;
        }
        return deep;
    }

    /**
     *  19.二叉树中和为某一值的路径
     *  思路:采用前序遍历,用一个栈保存之前遍历过的节点,当遍历到叶子节点时判断栈里节点之和是否等于K,
     *  每次递归回到父节点时把栈里的子节点都得删除
     */
    void findNodeValueToK(Node root,int k){
          if(root== null){
              return ;
          }
        Stack<Integer> path=new Stack<Integer>();
           findPath(root,k,path);
    }

    void findPath(Node root,int k,Stack<Integer> path){
        if(root.left ==null && root.right ==null){
               if(root.value == k){
                     for(int i:path){
                         System.out.print( i+",");
                         System.out.print(root.value);
                     }
               }
        }else{
             path.push(root.value);
             findPath(root.left,k-root.value,path);
             findPath(root.right,k-root.value,path);
             path.pop();
        }
    }

    /**
     * 20.给定一个逆转数组,用二分法找出某个值{7,8,9,1,2,3,4,5}找 8
     * @param a
     * @param b
     * @return
     */
    int findValue(int[] a,int b){
        int low=0;
        int high=a.length-1;
        int middle=0;
        while (low<high){
            middle=(low+high)/2;
            if(a[middle]==b){
                return middle;
            }else if (a[middle]<a[high] ){
                 if(a[middle]<b && a[high]>=b){
                   low=middle+1;
                 }else{
                    high=middle-1;
                 }
            }else{
                if(a[middle]>b && a[low]<=b){
                    high=middle-1;
                }else{
                    low=low+1;
                }
            }
        }
        return  -1;
    }
    //找旋转数组的最小值
    int findMin(int[] a){
        int low=0;
        int high=a.length-1;
        int middle=0;
        while (low<high){
            middle=(low+high)/2;
            if(a[middle]<a[high]){
                high=middle;
            }else{
                low=middle+1;
            }
        }
        return low;
    }

    int findFirstValue(int[] a,int b ){
        int low=0;
        int high=a.length-1;
        int middle=0;
        while (low<high){
            middle=(low+high)/2;
            if(a[middle]<b){
                low=middle+1;
            }else{
                high=middle;
            }
        }
        if(a[low] !=b){
            return -1;
        }else{
            return low;
        }
    }
}