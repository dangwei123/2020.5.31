请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 
public class Solution {
    private int[] dx={1,-1,0,0};
    private int[] dy={0,0,1,-1};
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        for(int i=0;i<rows*cols;i++){
            if(back(matrix,rows,cols,str,i,new boolean[rows*cols],0)){
                return true;
            }
        }
        return false;
    }
    
    private boolean back(char[] matrix,int rows,int cols,char[] str,int begin,
                         boolean[] isVisited,int index){
        int i=begin/cols;
        int j=begin%cols;
        if(index==str.length) return true;
        if(i<0||j<0||i>=rows||j>=cols||matrix[begin]!=str[index]||isVisited[begin]){
            return false;
        }
        
        isVisited[begin]=true;
        index+=1;
        for(int k=0;k<4;k++){
            int x=i+dx[k];
            int y=j+dy[k];
            if(back(matrix,rows,cols,str,x*cols+y,isVisited,index)){
                return true;
            }
        }
        isVisited[begin]=false;
        index-=1;
        return false;
    }



}


地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和
列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），
因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
public class Solution {
    private int max;
    public int movingCount(int threshold, int rows, int cols){
        back(threshold,rows,cols,0,0,new boolean[rows][cols]);
        return max;
    }
    private void back(int threshold,int rows,int cols,int i,int j,boolean[][] v){
        if(i<0||j<0||i>=rows||j>=cols||(sum(i)+sum(j)>threshold)||v[i][j]){
            return;
        }
        max++;
        v[i][j]=true;
        back(threshold,rows,cols,i+1,j,v);
        back(threshold,rows,cols,i-1,j,v);
        back(threshold,rows,cols,i,j+1,v);
        back(threshold,rows,cols,i,j-1,v);
    }
    private int sum(int n){
        int res=0;
        while(n!=0){
            res+=(n%10);
            n/=10;
        }
        return res;
    }
}


给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。
请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到
的最大乘积是18。
public class Solution {
    public int cutRope(int target) {
        if(target<3) return 1;
        if(target==3) return 2;
        int n=target/3;
        int m=target%3;
        if(m==0) return (int)Math.pow(3,n);
        if(m==1) return (int)Math.pow(3,n-1)*4;
        if(m==2) return (int)Math.pow(3,n)*2;
        return 1;
    }
}

这天，牛牛来到了一个台阶的面前。

台阶一共有{n}n层，有一些台阶上有积水。

牛牛一开始在第0层，它每次可以跳奇数层台阶，他想跳到第n层，但是它不希望在跳跃的过程中踩到积水。

已知有{m}m个台阶上有积水。

请问牛牛在不踩到积水的情况下跳到第n层有多少种不同的方案。如果不可能到达第{n}n层，则答案为0。

为了防止答案过大，答案对1e9+7取模。

import java.util.*;


public class Solution {
    /**
     * 又见台阶
     * @param n int整型 
     * @param m int整型 
     * @param a int整型一维数组 
     * @return int整型
     */
    public int solve (int n, int m, int[] a) {
        // write code here
        int mod=1000000007;
        int[] dp=new int[n+1];
        for(int i:a){
            dp[i]=-1;
        }
        if(dp[n]==-1) return 0;
        dp[0]=1;
        int pre=0;
        int cur=1;
        for(int i=1;i<=n;i++){
            if(dp[i]!=-1){
               if((i&1)==0){
                   cur=(pre+cur)%mod;
               }else{
                   pre=(pre+cur)%mod;
               }
            }
        }
        return (n&1)==1?cur:pre;
    }
}


