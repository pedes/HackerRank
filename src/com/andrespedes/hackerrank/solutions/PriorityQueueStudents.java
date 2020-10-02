package com.andrespedes.hackerrank.solutions;

import java.util.*;

public class PriorityQueueStudents {

    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    /**
     * Entry to test this method.
     * 12
     * ENTER John 3.75 50
     * ENTER Mark 3.8 24
     * ENTER Shafaet 3.7 35
     * SERVED
     * SERVED
     * ENTER Samiha 3.85 36
     * SERVED
     * ENTER Ashley 3.9 42
     * ENTER Maria 3.6 46
     * ENTER Anik 3.95 49
     * ENTER Dan 3.95 50
     * SERVED
     *
     * Expected output:
     * Dan
     * Ashley
     * Shafaet
     * Maria
     * @param args
     */
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }

    static class Student {

        String name;
        double CGPA;
        int ID;

        public Student(int id, String name, double cgpa) {
            this.name = name;
            this.CGPA = cgpa;
            this.ID = id;
        }

        public String getName() {
            return this.name;
        }

        public double getCGPA() {
            return CGPA;
        }

        public int getID() {
            return ID;
        }
    }

    static class Priorities {

        public List<Student> getStudents(List<String> events) {

            Comparator<Student> studentComparator = new Comparator<Student>() {
                @Override
                public int compare(Student std1, Student std2)  {
                    if (std1.getCGPA() == std2.getCGPA()) {
                        int nameComparison = std1.getName().compareTo(std2.getName());
                        if(nameComparison == 0){
                            return Integer.compare(std2.getID(), std1.getID());
                        } else{
                            return nameComparison;
                        }
                    }
                    return Double.valueOf(std2.getCGPA()).compareTo(std1.getCGPA());
                }
            };

            Queue<Student> pq = new PriorityQueue<Student>(studentComparator);

            for (String event : events) {
                if ("SERVED".equalsIgnoreCase(event)) {
                    pq.poll();
                } else {
                    String[] entry = new String[4];
                    entry = event.split("\\s");
                    pq.add(new Student(Integer.valueOf(entry[3]), entry[1], Double.valueOf(entry[2])));
                }
            }

            List<Student> studentlist = new ArrayList<Student>(pq);
            Collections.sort(studentlist,studentComparator);
            return studentlist;
        }
    }
}
