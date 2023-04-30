public class Util {

    public static float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (float)Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1) );
    }

    public static boolean on_line(float px, float py, float pz, float x1, float y1, float z1, float x2, float y2, float z2) {
        return distance(x1,y1,z1,px,py,pz) + distance(x2,y2,z2,px,py,pz) == distance(x1,y1,z1,x2,y2,z2);
    }

    public static boolean inRadius(float sx, float sy, float sz, float r, float px, float py, float pz) {
        return (sx-px)*(sx-px) + (sy-py)*(sy-py) + (sz-pz)*(sz-pz) < r*r;
    }
}
