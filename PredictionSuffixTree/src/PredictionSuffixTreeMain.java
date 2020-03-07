
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

public class PredictionSuffixTreeMain extends PApplet {
	public static void main(String[] args) {
		PApplet.main("PredictionSuffixTreeMain"); 
	}
	
	Tree<Character> newTree1 = new Tree();
	Tree<Character> newTree2 = new Tree();
	Tree<Character> newTree3 = new Tree();
	
	//Iput Arrays
	Character[] list1 = {'a','b','r','a','c','a','d','a','b','r','a'};
	Character[] list2 = {'a','c','a','d','a','a','c','b','d','a'};
	Character[] list3 = {'a','b','c','c','c','d','a','a','d','c','d','a','a','b','c','a','d','a','d'};
	//Change to ArrayList
	ArrayList<Character> test1 = new ArrayList(Arrays.asList(list1));
	ArrayList<Character> test2 = new ArrayList(Arrays.asList(list2));
	ArrayList<Character> test3 = new ArrayList(Arrays.asList(list3));
	
	//Mary Had a Little Lamb
	//ArrayListString test4 = "Mary Had a Little Lamb";
	
	public void settings() {
		size(300, 300);
	}

	public void setup() {
		fill(120, 50, 240);
	
		//train
		System.out.println("Test Sequence = " + test1);
		newTree1.train(test1);
		newTree1.print();
		
		System.out.println("Test Sequence = " + test2);
		newTree2.train(test2);
		newTree2.print();
		
		System.out.println("Test Sequence = " + test3);
		newTree3.train(test3);
		newTree3.print();
	}
	
	public void draw() {
		
	}
	
	
	
}

