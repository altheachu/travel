package ecommerce.travel.aop;

public enum Timing {
    AfterOrder("afterOrder");

    Timing(String timingStr){
        this.timingStr = timingStr;
    }

    private String timingStr;
}
