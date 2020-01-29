import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.function.Consumer;

class DataList<T> implements Iterable<T>{

    private Node head;
    private Node tail;

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if(hasNext()){
                    T data = current.data;
                    current = current.next;
                    return data;
                }
                return null;
            }
        };
    }

    private class Node{
        public T data;
        public Node next;
        public Node prev;
        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
        Node(){
            this.next = null;
            this.prev = null;
        }
    }

    public DataList(){
        head = null;
        tail = null;
    }

    public boolean isNull(){
        return head == null;
    }

    public void Print(){
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
    }

    public void Add(T value){
        if (head == null){
            head = new Node(value);
            tail = head;
        }
        else{
            Node tmp = new Node(value);
            tmp.prev = tail;
            tail.next = tmp;
            tail = tmp;
        }
    }

    public void Remove(T value) {
        if(isNull()){
            return;
        }
        if (head.data == value && head.next != null) {
            head = head.next;
            head.prev = null;
            return;
        }else if(head.data == value && tail == head){
            head.next = null;
            head = null;
            return;
        }
        if(tail.data == value){
            tail = tail.prev;
            tail.next = null;
            return;
        }
        Node temp = head, tret;
        while(temp.data != value){
            temp = temp.next;
        }
        tret = temp;
        tret.prev.next = temp.next;
        tret.next.prev = temp.prev;
    }

}
