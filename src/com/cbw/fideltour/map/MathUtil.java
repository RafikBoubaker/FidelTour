package com.cbw.fideltour.map;


import static java.lang.Math.*;

class MathUtil {
   
    static final double EARTH_RADIUS = 6371009;

    static double clamp(double x, double low, double high) {
        return x < low ? low : (x > high ? high : x);
    }
    
    static double wrap(double n, double min, double max) {
        return (n >= min && n < max) ? n : (mod(n - min, max - min) + min);
    }

    static double mod(double x, double m) {
        return ((x % m) + m) % m;
    }

   
    static double mercator(double lat) {
        return log(tan(lat * 0.5 + PI/4));
    }

    static double inverseMercator(double y) {
        return 2 * atan(exp(y)) - PI / 2;
    }
    
    /**
     * Returns haversine(angle-in-radians).
     * hav(x) == (1 - cos(x)) / 2 == sin(x / 2)^2.
     */
    static double hav(double x) {
        double sinHalf = sin(x * 0.5);
        return sinHalf * sinHalf;
    }

    /**
     * Computes inverse haversine. Has good numerical stability around 0.
     * arcHav(x) == acos(1 - 2 * x) == 2 * asin(sqrt(x)).
     * The argument must be in [0, 1], and the result is positive.
     */
    static double arcHav(double x) {
        return 2 * asin(sqrt(x));
    }
    
    // Given h==hav(x), returns sin(abs(x)).
    static double sinFromHav(double h) {
        return 2 * sqrt(h * (1 - h));
    }

    // Returns hav(asin(x)).
    static double havFromSin(double x) {
        double x2 = x * x;
        return x2 / (1 + sqrt(1 - x2)) * .5;
    }

    // Returns sin(arcHav(x) + arcHav(y)).
    static double sinSumFromHav(double x, double y) {
        double a = sqrt(x * (1 - x));
        double b = sqrt(y * (1 - y));
        return 2 * (a + b - 2 * (a * y + b * x));
    }

    /**
     * Returns hav() of distance from (lat1, lng1) to (lat2, lng2) on the unit sphere.
     */
    static double havDistance(double lat1, double lat2, double dLng) {
        return hav(lat1 - lat2) + hav(dLng) * cos(lat1) * cos(lat2);
    }
}
