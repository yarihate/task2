public class IntegralCalculator {
    public static void main(String[] args) {
		// (startPoint, endPoint, segmentLength)
        Segment.setParam(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
        try {
            for (int i = 0; i < (Segment.getEndPoint() - Segment.getStartPoint()) / Segment.getSegmentLength(); i++) {
                Thread t = new Thread(new Segment(i));
                t.start();
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Segment.getArea());
    }
}

class Segment implements Runnable {
    private static volatile double area;

    private static double startPoint;
    private static double endPoint;
    private static double segmentLength;
    private int count;

    static void setParam(double a, double b, double h) {
        startPoint = a;
        endPoint = b;
        segmentLength = h;
    }

    static double getStartPoint() {
        return startPoint;
    }

    static double getEndPoint() {
        return endPoint;
    }

    static double getSegmentLength() {
        return segmentLength;
    }

    static double getArea() {
        return area;
    }

    Segment(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        integralSq(startPoint, segmentLength);
    }

    private synchronized void integralSq(double a, double h) {
        area += h * Math.pow((a + this.count * h), 2);
    }
}

