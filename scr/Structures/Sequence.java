package Structures;
public interface Sequence<T> {
	void insereQueue(T element);
	void insereTete(T element);
	T extraitTete();
	boolean estVide();
	Iterateur<T> iterateur();
}