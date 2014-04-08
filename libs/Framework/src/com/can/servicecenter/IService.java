package com.can.servicecenter;


public interface IService{
	
	public abstract void releaseService();
	
	public abstract boolean isAlive();
	
	public abstract void addRef();
	
	public abstract void releaseRef();
	
}



