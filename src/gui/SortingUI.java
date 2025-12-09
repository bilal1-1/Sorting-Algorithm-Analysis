package gui;

import algorithms.*;
import datastructures.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;


public class SortingUI extends JFrame {

    // --- GENEL BİLEŞENLER ---
    private JTabbedPane tabbedPane;

    // --- 1. ALGORİTMA SEKMESİ ---
    private DefaultCategoryDataset dataset;
    private JTextArea algoLogArea;
    private JCheckBox chkBubble, chkMerge, chkQuick, chkHeap, chkSelection;
    private JButton btnRunSelected;
    private ChartPanel chartPanel;

    // --- 2. VERİ YAPILARI SEKMESİ ---
    private JComboBox<String> dsSelector;
    private JPanel dsInteractionPanel;
    private JTextArea dsLogArea;
    
    // Nesneler
    private SinglyLinkedList myLinkedList;
    private Heap myHeap;
    private DynamicArray myDynamicArray;
    private Randomgenerator myRandomGen;

    public SortingUI() {
        super("Algorithm & Data Structure Laboratory");
        setSize(1200, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Nesneleri başlat
        myLinkedList = new SinglyLinkedList();
        myHeap = new Heap(20); 
        myDynamicArray = new DynamicArray(5);
        myRandomGen = new Randomgenerator(571);

        // Sekmeli yapı
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        tabbedPane.addTab("Sorting Algorithms", createAlgorithmsPanel());
        tabbedPane.addTab("Data Structures", createDataStructuresPanel());

        add(tabbedPane);
        setVisible(true);
    }

    
    // -----------------------  ALGORİTMALAR PANELİ  ----------------------
    
    private JPanel createAlgorithmsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblSelect = new JLabel("Select Algorithms:"); 
        lblSelect.setFont(new Font("Arial", Font.BOLD, 14));
        controlPanel.add(lblSelect);
        controlPanel.add(Box.createVerticalStrut(10));

        chkBubble = new JCheckBox("Bubble Sort");
        chkMerge = new JCheckBox("Merge Sort");
        chkQuick = new JCheckBox("Quick Sort");
        chkHeap = new JCheckBox("Heap Sort");
        chkSelection = new JCheckBox("Selection Sort");



        controlPanel.add(chkBubble);
        controlPanel.add(chkMerge);
        controlPanel.add(chkQuick);
        controlPanel.add(chkHeap);
        controlPanel.add(chkSelection);
        controlPanel.add(Box.createVerticalStrut(20));

        btnRunSelected = new JButton("Run");
        btnRunSelected.setAlignmentX(Component.LEFT_ALIGNMENT);
        controlPanel.add(btnRunSelected);

        panel.add(controlPanel, BorderLayout.WEST);

        dataset = new DefaultCategoryDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        panel.add(chartPanel, BorderLayout.CENTER);

        algoLogArea = new JTextArea(8, 50);
        algoLogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(algoLogArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        btnRunSelected.addActionListener(e -> runBenchmark());

        return panel;
    }

    
    // -----------------------  VERİ YAPILARI PANELİ -------------------
    
    private JPanel createDataStructuresPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Seçim Menüsü
        JPanel topPanel = new JPanel();
        JLabel lblChoose = new JLabel("Select Data Structure: ");
        String[] options = {"LinkedList", "Dynamic Array", "Heap", "Random Generator"};
        dsSelector = new JComboBox<>(options);
        topPanel.add(lblChoose);
        topPanel.add(dsSelector);
        panel.add(topPanel, BorderLayout.NORTH);

        // Değişken Panel (CardLayout)
        dsInteractionPanel = new JPanel(new CardLayout());
        
        dsInteractionPanel.add(createLinkedListControls(), "LinkedList");
        dsInteractionPanel.add(createDynamicArrayControls(), "Dynamic Array");
        dsInteractionPanel.add(createHeapControls(), "Heap");
        dsInteractionPanel.add(createRandomGenControls(), "Random Generator");

        panel.add(dsInteractionPanel, BorderLayout.CENTER);

        // Log Alanı
        dsLogArea = new JTextArea(10, 50);
        dsLogArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        dsLogArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dsLogArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Operation Logs"));
        panel.add(scrollPane, BorderLayout.SOUTH);

        dsSelector.addActionListener(e -> {
            CardLayout cl = (CardLayout) (dsInteractionPanel.getLayout());
            cl.show(dsInteractionPanel, (String) dsSelector.getSelectedItem());
            dsLogArea.setText("--- Switched to: " + dsSelector.getSelectedItem() + " ---\n");
        });

        return panel;
    }

    // --- LinkedList Kontrolleri ---
    
    private JPanel createLinkedListControls() {
        // 1. Ana Panel 
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // 2. Satırları tutacak olan iç panel
        JPanel controlsHolder = new JPanel(new GridLayout(2, 1, 0, 5)); 

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextField tfInput = new JTextField(5);
        JButton btnAddFirst = new JButton("Add First");
        JButton btnAddLast = new JButton("Add Last");
        JButton btnRemove = new JButton("Remove (Value)");
        JButton btnContains = new JButton("Contains?");
        JButton btnSize = new JButton("Get Size");
        JButton btnEmpty = new JButton("Is Empty?");
        JButton btnPrint = new JButton("Print List");

        row1.add(new JLabel("Value:"));
        row1.add(tfInput);
        row1.add(btnAddFirst);
        row1.add(btnAddLast);
        row1.add(btnRemove);

        row2.add(btnContains);
        row2.add(btnSize);
        row2.add(btnEmpty);
        row2.add(btnPrint);

        // Satırları tutucuya ekle
        controlsHolder.add(row1);
        controlsHolder.add(row2);

        // 3. Tutucuyu ana panelin TEPESİNE ekle
        mainPanel.add(controlsHolder, BorderLayout.NORTH);

        // -- Aksiyonlar --
        btnAddFirst.addActionListener(e -> {
            try {
                int val = Integer.parseInt(tfInput.getText());
                myLinkedList.addFirst(val);
                dsLogArea.append("LinkedList: addFirst(" + val + ") success.\n");
                tfInput.setText("");
            } catch(Exception ex) { dsLogArea.append("Error: Invalid number.\n"); }
        });

        btnAddLast.addActionListener(e -> {
            try {
                int val = Integer.parseInt(tfInput.getText());
                myLinkedList.addLast(val);
                dsLogArea.append("LinkedList: addLast(" + val + ") success.\n");
                tfInput.setText("");
            } catch(Exception ex) { dsLogArea.append("Error: Invalid number.\n"); }
        });

        btnRemove.addActionListener(e -> {
            try {
                int val = Integer.parseInt(tfInput.getText());
                boolean result = myLinkedList.remove(val);
                if(result) dsLogArea.append("LinkedList: Removed value " + val + ".\n");
                else dsLogArea.append("LinkedList: Value " + val + " not found.\n");
                tfInput.setText("");
            } catch(Exception ex) { dsLogArea.append("Error: Invalid number.\n"); }
        });

        btnContains.addActionListener(e -> {
            try {
                int val = Integer.parseInt(tfInput.getText());
                dsLogArea.append("LinkedList: Contains " + val + "? -> " + myLinkedList.contains(val) + "\n");
            } catch(Exception ex) { dsLogArea.append("Error: Invalid number.\n"); }
        });

        btnSize.addActionListener(e -> dsLogArea.append("LinkedList Size: " + myLinkedList.getSize() + "\n"));
        btnEmpty.addActionListener(e -> dsLogArea.append("LinkedList Is Empty? -> " + myLinkedList.isEmpty() + "\n"));

        btnPrint.addActionListener(e -> {
            dsLogArea.append("--------------------------\n");
            dsLogArea.append("Current List: " + myLinkedList.toString() + "\n");
            dsLogArea.append("--------------------------\n");
            dsLogArea.setCaretPosition(dsLogArea.getDocument().getLength());
        });

        return mainPanel;
    }

    // ---  Dynamic Array Kontrolleri ---
    
    private JPanel createDynamicArrayControls() {
        // Ana Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // İç tutucu (Satırlar arası 5px boşluk)
        JPanel controlsHolder = new JPanel(new GridLayout(2, 1, 0, 5));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextField tfIndex = new JTextField(3);
        JTextField tfValue = new JTextField(5);
        
        JButton btnAdd = new JButton("Add (Value)");
        JButton btnGet = new JButton("Get (Index)");
        JButton btnSet = new JButton("Set (Index, Value)");
        JButton btnRemove = new JButton("Remove (Index)");
        JButton btnSize = new JButton("Size");

        row1.add(new JLabel("Index:"));
        row1.add(tfIndex);
        row1.add(new JLabel("Value:"));
        row1.add(tfValue);
        
        
        row2.add(btnSize); 
        
        row1.add(btnAdd);
        row2.add(btnGet);
        row2.add(btnSet);
        row1.add(btnRemove);

        controlsHolder.add(row1);
        controlsHolder.add(row2);
        
        // Tepeye sabitle
        mainPanel.add(controlsHolder, BorderLayout.NORTH);

        // -- Aksiyonlar --
        btnAdd.addActionListener(e -> {
            try {
                int val = Integer.parseInt(tfValue.getText());
                myDynamicArray.add(val);
                dsLogArea.append("Array: Added " + val + ". (New Size: " + myDynamicArray.size() + ")\n");
                tfValue.setText("");
            } catch(Exception ex) { dsLogArea.append("Error: Check Value input.\n"); }
        });

        btnGet.addActionListener(e -> {
            try {
                int idx = Integer.parseInt(tfIndex.getText());
                int val = myDynamicArray.get(idx);
                dsLogArea.append("Array: Element at index " + idx + " is " + val + ".\n");
            } catch(Exception ex) { dsLogArea.append("Error: Index out of bounds or invalid input.\n"); }
        });

        btnSet.addActionListener(e -> {
            try {
                int idx = Integer.parseInt(tfIndex.getText());
                int val = Integer.parseInt(tfValue.getText());
                myDynamicArray.set(idx, val);
                dsLogArea.append("Array: Set index " + idx + " to " + val + ".\n");
            } catch(Exception ex) { dsLogArea.append("Error: Check inputs or Index range.\n"); }
        });

        btnRemove.addActionListener(e -> {
            try {
                int idx = Integer.parseInt(tfIndex.getText());
                int removedVal = myDynamicArray.remove(idx);
                dsLogArea.append("Array: Removed element at index " + idx + " (Value was: " + removedVal + ").\n");
            } catch(Exception ex) { dsLogArea.append("Error: Index out of bounds.\n"); }
        });

        btnSize.addActionListener(e -> dsLogArea.append("Array Current Size: " + myDynamicArray.size() + "\n"));

        return mainPanel;
    }

    // --- Heap Kontrolleri ---
    private JPanel createHeapControls() {
        JPanel p = new JPanel(new FlowLayout());
        JTextField tfInput = new JTextField(5);
        JButton btnInsert = new JButton("Insert");
        JButton btnExtract = new JButton("Extract Max");

        p.add(new JLabel("Value:"));
        p.add(tfInput);
        p.add(btnInsert);
        p.add(btnExtract);

        btnInsert.addActionListener(e -> {
            try {
                int val = Integer.parseInt(tfInput.getText());
                myHeap.insert(val);
                dsLogArea.append("Heap: Inserted " + val + ".\n");
                tfInput.setText("");
            } catch(Exception ex) { dsLogArea.append("Error: " + ex.getMessage() + "\n"); }
        });

        btnExtract.addActionListener(e -> {
            try {
                int val = myHeap.extractMax();
                dsLogArea.append("Heap: Extracted Max -> " + val + "\n");
            } catch(Exception ex) { dsLogArea.append("Error: Heap is empty.\n"); }
        });

        return p;
    }

    // --- Random Generator Kontrolleri ---
    private JPanel createRandomGenControls() {
        JPanel p = new JPanel(new FlowLayout());
        JTextField tfSize = new JTextField("", 5);
        JTextField tfMax = new JTextField("", 5);
        JButton btnGenerate = new JButton("Generate Array");

        p.add(new JLabel("Size (n):"));
        p.add(tfSize);
        p.add(new JLabel("Max Value:"));
        p.add(tfMax);
        p.add(btnGenerate);

        btnGenerate.addActionListener(e -> {
            try {
                int n = Integer.parseInt(tfSize.getText());
                int max = Integer.parseInt(tfMax.getText());
                
                int[] result = myRandomGen.generate(n, max);
                
                dsLogArea.append("RandomGen: Generated " + n + " numbers (Max: " + max + "):\n");
                dsLogArea.append(Arrays.toString(result) + "\n\n");
                dsLogArea.setCaretPosition(dsLogArea.getDocument().getLength());
            } catch(Exception ex) { dsLogArea.append("Error: Check inputs.\n"); }
        });

        return p;
    }

    
    // ----------------------- GRAFİK VE ALGORİTMA TEST MANTIĞI ------------------------
    

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart lineChart = ChartFactory.createLineChart(
            "Algorithm Performance Analysis",
            "Array Size (n)",
            "Time (ms - Log Scale)",
            dataset
    );

    // === CHART DARK BACKGROUND ===
    lineChart.setBackgroundPaint(new Color(30, 30, 30));

    CategoryPlot plot = lineChart.getCategoryPlot();
    
    plot.setBackgroundPaint(new Color(20, 20, 20));   // Grafik içi
    plot.setDomainGridlinePaint(Color.GRAY);          // X çizgileri
    plot.setRangeGridlinePaint(Color.GRAY);           // Y çizgileri

    // === EKSEN YAZILARI BEYAZ ===
    plot.getDomainAxis().setLabelPaint(Color.WHITE);
    plot.getRangeAxis().setLabelPaint(Color.WHITE);
    plot.getDomainAxis().setTickLabelPaint(Color.WHITE);
    plot.getRangeAxis().setTickLabelPaint(Color.WHITE);

    LogarithmicAxis logAxis = new LogarithmicAxis("Time (ms - Log Scale)");
    logAxis.setAllowNegativesFlag(true);
    logAxis.setLabelPaint(Color.WHITE);
    logAxis.setTickLabelPaint(Color.WHITE);
    plot.setRangeAxis(logAxis);

    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

    renderer.setSeriesPaint(0, Color.RED);
    renderer.setSeriesPaint(1, Color.BLUE);
    renderer.setSeriesPaint(2, Color.GREEN);
    renderer.setSeriesPaint(3, Color.MAGENTA);
    renderer.setSeriesPaint(4, Color.ORANGE);

    for (int i = 0; i < 5; i++)
        renderer.setSeriesStroke(i, new BasicStroke(3.0f));

    return lineChart;
    }

    private void runBenchmark() {
        btnRunSelected.setEnabled(false);
        dataset.clear();
        algoLogArea.setText("Benchmark Started...\n");

        new Thread(() -> {
            int[] sizes = {1000, 5000, 10000, 20000, 50000};
            int trials = 3; 

            Randomgenerator rgTest = new Randomgenerator(571);

            for (int n : sizes) {
                SwingUtilities.invokeLater(() -> algoLogArea.append("--- Processing Size: " + n + " ---\n"));
                
                if (chkBubble.isSelected()) runAlgo("Bubble Sort", n, trials, rgTest);
                if (chkMerge.isSelected()) runAlgo("Merge Sort", n, trials, rgTest);
                if (chkQuick.isSelected()) runAlgo("Quick Sort", n, trials, rgTest);
                if (chkHeap.isSelected()) runAlgo("Heap Sort", n, trials, rgTest);
                if (chkSelection.isSelected()) runAlgo("Selection Sort", n, trials, rgTest);
            }

            SwingUtilities.invokeLater(() -> {
                algoLogArea.append("\n✅ BENCHMARK COMPLETED!\n");
                btnRunSelected.setEnabled(true);
            });
        }).start();
    }

    private void runAlgo(String name, int n, int trials, Randomgenerator rg) {
        double totalTime = 0;
        for (int t = 0; t < trials; t++) {
            
            //rastgele dizi
            int[] arr = rg.generate(n, 500000);

            long start = System.nanoTime();
            
            if (name.equals("Bubble Sort")) BubbleSort.sort(arr.clone());
            else if (name.equals("Merge Sort")) MergeSort.sort(arr.clone());
            else if (name.equals("Quick Sort")) QuickSort.sort(arr.clone(), 0, arr.length - 1);
            else if (name.equals("Heap Sort")) HeapSort.sort(arr.clone());
            else if (name.equals("Selection Sort")) SelectionSort.sort(arr.clone());
            
            long end = System.nanoTime();
            totalTime += (end - start) / 1_000_000.0;
        }
        
        double avg = totalTime / trials;
        
        SwingUtilities.invokeLater(() -> {
            dataset.addValue(avg, name, String.valueOf(n));
            algoLogArea.append(name + " (" + n + "): " + String.format("%.2f", avg) + " ms\n");
            algoLogArea.setCaretPosition(algoLogArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        try {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        // === DARK THEME AYARLARI ===
        UIManager.put("control", new Color(45, 45, 45));
        UIManager.put("info", new Color(60, 63, 65));
        UIManager.put("nimbusBase", new Color(18, 30, 49));
        UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
        UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
        UIManager.put("nimbusFocus", new Color(115, 164, 209));
        UIManager.put("nimbusGreen", new Color(176, 179, 50));
        UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
        UIManager.put("nimbusLightBackground", new Color(30, 30, 30));
        UIManager.put("nimbusOrange", new Color(191, 98, 4));
        UIManager.put("nimbusRed", new Color(169, 46, 34));
        UIManager.put("nimbusSelectedText", Color.WHITE);
        UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
        UIManager.put("text", Color.WHITE);

    } catch (Exception e) {}

    SwingUtilities.invokeLater(SortingUI::new);
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
        
        SwingUtilities.invokeLater(SortingUI::new);
    }
}
