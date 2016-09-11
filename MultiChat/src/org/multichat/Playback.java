package org.multichat;

// $Id: org.multichat.Playback.java,v 1.5 2001/05/04 21:13:09 mito Exp $

import org.multichat.client.ClientShared;

import javax.sound.sampled.*;

public class Playback implements Runnable {
    // Data written to this is played by the soundcard
    private SourceDataLine sdl;
    
    // Write this many bytes per inner loop execution
    static private final int innerLoopWriteSize = 2048;
    
    // new sounds to be played are placed on this queue
    private Queue incoming = new Queue();
    
    // lock to wait on when waiting for a sound to play
    private Object soundLock = new Object();
    
    // assume a standard sampling rate
    static final public int sampRate = 8000;
    
    // the latency through the low-level sound system
    // this must be tuned for each system
    static private final double sysLatencyTime = 0.695;
    
    // the sound latency expressed in samples
    static private final int sysLatency =
    (int)(sysLatencyTime*sampRate);
    
    // a standard audio format for playing audio
    static public AudioFormat stdFormat =
    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, ClientShared.sampleRate, ClientShared.sampleSize, 1, 1, ClientShared.frameRate, false);
    
    // The construtor opens a playback audio line
    // and creates a background thread for streaming data
    public Playback() {
        // Open the playback line
        sdl = getOutputLine();
        
        // Create a background thread for streaming audio
        Thread t = new Thread( this, "playback" );
        t.start();
    }
    
    
    public Playback( byte b[] ){
        sdl = getOutputLine();
        // Create a background thread for streaming audio
        Thread t = new Thread( this, "playback" );
        t.start();
        this.setSound( b );
    }
    
    
    // Tell the playback to play the next sound
    // set to null to turn sound off
    public void setSound( byte raw[] ) {
        synchronized(soundLock) {
            // place the new sound on the queue
            incoming.put( raw );
            
            // tell the thread that there's a new sound
            soundLock.notifyAll();
        }
    }
    
    // Background streaming thread
    public void run() {
        // The currently playing sound
        byte currentRaw[] = null;
        
        // The position within the currently playing sound
        int cursor = 0;
        
        // open the output line for playing
        try {
            // open it with our standard audio format
            sdl.open( stdFormat );
            
            // start it playing
            sdl.start();
        } catch( LineUnavailableException lue ) {
            throw new RuntimeException( lue.toString() );
        }
        
        while (true) {
            synchronized (soundLock) {                
                while( true ){
                    if(incoming.numWaiting()>0){
                        currentRaw = (byte[])incoming.get();
                        break;
                    }else{
                        try {
                            soundLock.wait(50);
                        } catch( InterruptedException ie ) {}
                    }
                }
                cursor = 0;
                int bytesLeft = currentRaw.length - cursor;
                
                do{ 
                	if (sdl.available()>0){
			            int r = Math.min(sdl.available(), currentRaw.length-cursor);
	                    sdl.write( currentRaw, cursor, r );
	                    if (r==-1)
	                    	throw new RuntimeException( "Can't write to line!" );
	                    cursor+=r;
             		} else {
             			try {
                        	soundLock.wait(10);
                    	}catch( InterruptedException ie ) {}	
             		}
	                 	
                } while(currentRaw.length-cursor > 0);
            }
        }
    }
    
    // Utility class -- open an output line with our standard
    // audio format
    public SourceDataLine getOutputLine() {
        try {
            DataLine.Info info =
            new DataLine.Info( SourceDataLine.class, stdFormat );
            SourceDataLine sdl = (SourceDataLine)AudioSystem.getLine( info );
            return sdl;
        } catch( LineUnavailableException lue ) {
            throw new RuntimeException( "Can't get output line" );
        }
    }
}
