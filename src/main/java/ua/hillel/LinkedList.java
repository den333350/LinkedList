package ua.hillel;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public boolean contains(Object o) {

        return indexOf(o)>=0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = current.value;
                current= current.next;
                return value;
            }
        };
    }

    public Iterator<E> reverseIterator(){
        return new Iterator<>() {
            Node<E> current = tail;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = current.value;
                current= current.previous;
                return value;
            }
        };
    }

    public boolean add(E e) {
        Node<E> l = tail;
        Node<E> node= new Node<>(l,e,null);
        tail = node;
        if(l == null){
            head = node;
            size = 1;
        }else{
            l.next =  node;
            size++;
        }

        return true;
    }

    public boolean remove(Object o) {
        for (Node<E> i = head; i != null; i = i.next) {
            if (o.equals(i.value)) {
                unlink(i);
                return true;
            }
        }
        return false;
    }

    public void clear() {
        head=tail=null;
    }

    public E get(int index) {
        return returnNode(index).value;
    }

    public E set(int index, E element) {
        Node<E> e = returnNode(index);
        E oldValue = e.value;
        e.value = element;
        return oldValue;
    }

    public void add(int index, E element) {
        Node<E> next = returnNode(index);
        Node<E> prev = next.previous;
        Node<E> newNode = new Node<>(prev,element,next);
        next.previous=newNode;
        if(prev==null){
            head  = newNode;
        }
        prev.next = newNode;

        size++;

    }

    public E remove(int index) {
        return unlink(returnNode(index));
    }

    public int indexOf(Object o) {
        int index = 0;
        for(Node<E> i  = head;i!=null;i=i.next){
            if(o.equals(i.value)){
                return index;
            }
            index++;
        }
        return -1;
    }


    @Data @AllArgsConstructor
    private static class Node<E>{
        Node<E> previous;
        E value;
        Node<E> next;
    }
    Node <E> returnNode(int index){
        Node<E> x = head;
        for (int i = 0; i < index; i++) {
            x =  x.next;
        }
        return x;
    }
    private E unlink(Node<E> e){
        E value = e.value;
        Node<E> next = e.next;
        Node<E> prev = e.previous;

        if(next ==  null){
            tail = prev;
        }else  prev.next = next;
        if(prev == null){
            head = next;
        }else next.previous = prev;
        return value;
    }
}
