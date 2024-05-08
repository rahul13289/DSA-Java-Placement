class Solution {
    public String[] findRelativeRanks(int[] score) {
        String []ans = new String[score.length];
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0; i<score.length; i++){
            hm.put(score[i],i);
        }
        for(int i=0; i< score.length; i++){
            score[i] = score[i]*-1;
        }
        Arrays.sort(score);
        for(int i=0; i< score.length; i++){
            score[i] = score[i]*-1;
        }
        if(score.length == 1){
            ans[hm.get(score[0])] = "Gold Medal";
        }else if(score.length == 2){
            ans[hm.get(score[0])] = "Gold Medal";
            ans[hm.get(score[1])] = "Silver Medal";
        }else{
            ans[hm.get(score[0])] = "Gold Medal";
            ans[hm.get(score[1])] = "Silver Medal";
            ans[hm.get(score[2])] = "Bronze Medal";
        }
        
        for(int i=3; i<score.length; i++){
            ans[hm.get(score[i])]= ""+ (i+1); 
        }
        return ans;
    }
}
