package com.base.engine.core;

public class Vector3f {
    public static final Vector3f ZERO = new Vector3f(0, 0, 0);
    public static final Vector3f X = new Vector3f(1, 0, 0);
    public static final Vector3f Y = new Vector3f(0, 1, 0);
    public static final Vector3f Z = new Vector3f(0, 0, 1);
    
    private float x;
    private float y;
    private float z;
    
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }
    
    public float max() {
        return Math.max(x, Math.max(y, z));
    }
    
    public float dot(Vector3f r) {
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }
    
    public Vector3f cross(Vector3f r) {
        float x_ = y * r.getZ() - z * r.getY();
        float y_ = z * r.getX() - x * r.getZ();
        float z_ = x * r.getY() - y * r.getX();
        
        return new Vector3f(x_, y_, z_);
    }
    
    public Vector3f normalized() {
        float length = length();
        
        return new Vector3f(x / length, y / length, z / length);
    }
    
    // rotate around the axis
    public Vector3f rotate(Vector3f axis, float angle) {
        return rotate(new Quaternion(axis, angle));
//        float sinAngle = (float) Math.sin(-angle);
//        float cosAngle = (float) Math.cos(-angle);
//
//        return this.cross(axis.getTransformedPosition(sinAngle))
//                     .add((this.getTransformedPosition(cosAngle))
//                     .add(axis.getTransformedPosition(this.dot(axis.getTransformedPosition(1 - cosAngle)))));
    }
    
    public Vector3f rotate(Quaternion quaternion) {
        Quaternion conjugate = quaternion.conjugate();
        
        Quaternion w = quaternion.mul(this).mul(conjugate);
        
        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }
    
    public Vector3f lerp(Vector3f dest, float lerpFactor) {
        return dest.sub(this).mul(lerpFactor).add(this);
    }
    
    public Vector3f add(Vector3f r) {
        return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
    }
    
    public Vector3f add(float r) {
        return new Vector3f(x + r, y + r, z + r);
    }
    
    public Vector3f sub(Vector3f r) {
        return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
    }
    
    public Vector3f sub(float r) {
        return new Vector3f(x - r, y - r, z - r);
    }
    
    public Vector3f mul(Vector3f r) {
        return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
    }
    
    public Vector3f mul(float r) {
        return new Vector3f(x * r, y * r, z * r);
    }
    
    public Vector3f div(Vector3f r) {
        return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
    }
    
    public Vector3f div(float r) {
        return new Vector3f(x / r, y / r, z / r);
    }
    
    public Vector3f negate() {
        return new Vector3f(-x, -y, -z);
    }
    
    public Vector3f abs() {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }
    
    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
    
    public boolean equals(Vector3f other) {
        return x == other.getX() && y == other.getY() && z == other.getZ();
    }
    
    public Vector2f getXY() {
        return new Vector2f(x, y);
    }
    
    public Vector2f getYZ() {
        return new Vector2f(y, z);
    }
    
    public Vector2f getZX() {
        return new Vector2f(z, x);
    }
    
    public Vector2f getYX() {
        return new Vector2f(y, x);
    }
    
    public Vector2f getZY() {
        return new Vector2f(z, y);
    }
    
    public Vector2f getXZ() {
        return new Vector2f(x, z);
    }
    
    public float getX() {
        return x;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public float getZ() {
        return z;
    }
    
    public void setZ(float z) {
        this.z = z;
    }
    
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
