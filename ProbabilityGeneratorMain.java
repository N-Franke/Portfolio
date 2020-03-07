
/*
 * c2017-2019 Courtney Brown 
 * 
 * Class: H
 * Description: Demonstration of MIDI file manipulations, etc. & 'MelodyPlayer' sequencer
 * 
 */

import processing.core.*;
import java.util.ArrayList;

import java.util.*; 

//importing the JMusic stuff
import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;


//import javax.sound.midi.*;

			//make sure this class name matches your file name, if not fix.
public class ProbabilityGeneratorMain extends PApplet {

	MelodyPlayer player; //play a midi sequence

	MidiFileToNotes midiNotes; //read a midi file
	
	ProbabilityGenerator<Integer> notes = new ProbabilityGenerator<Integer>();
	ProbabilityGenerator<Double> rhythm = new ProbabilityGenerator<Double>();
	
//	MarkovGenerator<Integer> mNotes = new MarkovGenerator<Integer>();
//	MarkovGenerator<Double> mRhythm = new MarkovGenerator<Double>(); 
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("ProbabilityGeneratorMain"); //change this to match above class & file name 

	}

	//setting the window size to 300x300
	public void settings() {
		size(300, 300);

	}

	//doing all the setup stuff
	public void setup() {
		fill(120, 50, 240);

		// returns a url
		String filePath = getPath("mid/MaryHadALittleLamb.mid");
		playMidiFile(filePath);
		//if you change the blue, you change the song you hear 

		midiNotes = new MidiFileToNotes(filePath); //creates a new MidiFileToNotes -- reminder -- ALL objects in Java must 
													//be created with "new". Note how every object is a pointer or reference. Every. single. one.
		
//		// which line to read in --> this object only reads one line (or ie, voice or ie, one instrument)'s worth of data from the file
		midiNotes.setWhichLine(0);

		player = new MelodyPlayer(this, 100.0f);
		player.setup();
		
		//Mary Had a little lamb, Train Function Probability Generator
		notes.train(midiNotes.getPitchArray());
		rhythm.train(midiNotes.getRhythmArray());

		//Markov Generator
		//mNotes.train(midiNotes.getPitchArray());
		
	}

	public void draw() {
		
		text("To play the song, press  r", 30, 20);
		text("To run Test 1, press 1", 30, 30); 
		text("To run Test 2, press 2", 30, 40);
		text("To run Test 3, press 3", 30, 50);
		if(keyPressed) {
			if(key=='r') {
				player.setMelody(midiNotes.getPitchArray());
				player.setRhythm(midiNotes.getRhythmArray());
				player.play(); //play each note in the sequence -- the player will determine whether is time for a note onset
			}
			//Test 1
			if(key=='1') {
			System.out.println("Test 1");
			notes.probabilityDistribution();
			rhythm.probabilityDistribution();
			}
			//Test 2
			if(key=='2') {
			//How many times do you want to run generate?
			int number = 20;
			System.out.println("Test 2");
			System.out.println("Notes");
			notes.generateMult(number);
			notes.melodyGenerator(notes.generateMult(number));
			System.out.println("Rhythm");
			rhythm.generateMult(number);
			rhythm.melodyGenerator(rhythm.generateMult(number));
			}
			if(key=='3') {
			System.out.println("Test 3");
			int length = 10000;
			int number = 20;
			
			ArrayList<Integer> songPitch; 
			ArrayList<Double> songRhythm;	
			ProbabilityGenerator<Integer> newProbPitch = new ProbabilityGenerator<Integer>();
			ProbabilityGenerator<Double> newProbRhyt = new  ProbabilityGenerator<Double>();
			
			for (int i =0; i<length; i++) {
			
				//mary had a little lamb
				songPitch = notes.generateMult(number);
				songRhythm= rhythm.generateMult(number);
				
				newProbPitch.train(songPitch);
				newProbRhyt.train(songRhythm);
				
				//player.setMelody();
				// player.setRhythm(newSongRhythm);
			}
				System.out.println("Pitch");
				newProbPitch.probabilityDistribution();
				System.out.println("Rhythm");
				newProbRhyt.probabilityDistribution();
				player.play();
			}
		}
}

	//this finds the absolute path of a file
	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	//this function is not currently called. you may call this from setup() if you want to test
	//this just plays the midi file -- all of it via your software synth. You will not use this function in upcoming projects
	//but it could be a good debug tool.
	void playMidiFile(String filename) {
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

	//this starts & restarts the melody.
	public void keyPressed() {
		if (key == ' ') {
			player.reset();
			println("Melody started!");
		}
	}
}
