package de.szut.simNil.binaryMaple.gui;

import de.szut.simNil.binaryMaple.AbstractNode;
import de.szut.simNil.binaryMaple.BNode;
import de.szut.simNil.binaryMaple.InterfaceBinarySearchTree;
import de.szut.simNil.binaryMaple.Utils;
import de.szut.simNil.binaryMaple.rb.RBNode;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Node;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.*;

/**
 * graphviz generator
 *
 * @param <T> tree value type
 */
public class TreeVisualizer<T extends Comparable<T>> {
    /**
     * a unique id number for gnodes with duplicate names like null
     */
    private int duplicateNodeNumber = 0;

    /**
     * the tree which should be visualized
     */
    @NotNull
    @Setter
    @Getter
    private InterfaceBinarySearchTree<T> tree;
    /**
     * a list for gnodes
     */
    @NotNull
    private List<Node> nodes;

    /**
     * a node which should highlighted
     */
    @Getter
    @Setter
    @Nullable
    private AbstractNode<T> highlightedNode;

    /**
     * font name for graphviz, if null the default is taken
     */
    @Getter
    @Setter
    @Nullable
    private String font;

    /**
     * a cached graphviz for reusing
     */
    @Nullable
    private Graphviz graphviz;

    /**
     * A list with nodes where to collapse
     */
    private List<AbstractNode<T>> collapseNodes = new ArrayList<>();

    /**
     * If terminal nodes should be shown
     */
    @Getter
    @Setter
    private boolean showNullNodes = false;

    /**
     * If the leafs should have a green border
     */
    @Getter
    @Setter
    private boolean highlightLeafs = true;

    /**
     * If grass should be shown in the background
     */
    @Getter
    @Setter
    private boolean withGrass = true;

    /**
     * @param tree the tree to visualize
     */
    public TreeVisualizer(@NotNull InterfaceBinarySearchTree<T> tree) {
        this.tree = tree;
        this.nodes = new ArrayList<>(this.tree.getNodeCount());
    }

    /**
     * @param node the node where to collapse
     */
    public void addCollapseNode(AbstractNode<T> node) {
        this.collapseNodes.add(node);
    }

    /**
     * @param node the node to check if collapsed
     * @return if the node is collapsed
     */
    public boolean isCollapsed(AbstractNode<T> node) {
        return this.collapseNodes.contains(node);
    }

    /**
     * @param node the node to uncollapse
     */
    public void removeCollapseNode(AbstractNode<T> node) {
        this.collapseNodes.remove(node);
    }

    /**
     * @return all abstract nodes of the tree as graphviz nodes
     */
    @NotNull
    public List<Node> getNodes() {
        this.nodes.clear();
        this.duplicateNodeNumber = 0;
        addNode(this.tree.getRoot(), null);
        return this.nodes;
    }

    /**
     * Creates a graphviz node from the root node, sets the style, does the same process with its childs and adds the gnode to a list
     *
     * @param node   the node to create a gnode from
     * @param parent the parent node, needed for linking
     */
    private Node addNode(@Nullable AbstractNode<T> node, @Nullable Node parent) {
        Node me = null;
        if (node != null && node.getValue() != null) {

            String text = Utils.makeBeautifulMultiline(node.getValue().toString());

            if (!this.collapseNodes.contains(node)) {

                me = node(text).with(Style.FILLED.and(Style.lineWidth(2)), Color.WHITE.fill(), Color.BLACK.font());

                if (node instanceof BNode) {
                    if (node instanceof RBNode) {
                        switch (((RBNode) node).getColor()) {
                            case RED:
                                me = me.with(Color.RED.fill(), Color.RED);
                                break;
                            case BLACK:
                                me = me.with(Color.BLACK.fill(), Color.WHITE.font());
                                break;
                        }
                    }

                    BNode<T> left = ((BNode<T>) node).getLeft();
                    BNode<T> right = ((BNode<T>) node).getRight();
                    if (this.highlightLeafs && left != null && right != null
                        && left.getValue() == null && right.getValue() == null) {
                        me = me.with(Color.GREEN);
                    }

                    me = addNode(left, me);

                    me = addNode(right, me);
                }

                if (this.tree.getRoot().equals(node)) {
                    me = me.with(Color.BROWN.fill(), Color.BROWN, Color.WHITE.font());
                }

                if (this.highlightedNode != null && this.highlightedNode.getValue() != null
                    && this.highlightedNode.getValue().compareTo(node.getValue()) == 0) {
                    me = me.with(Color.PURPLE.fill(), Color.WHITE.font());
                }
            } else {
                me = node(String.format("collapse%d", duplicateNodeNumber++)).with(Label.of(text), Shape.TRIANGLE);
            }
        } else {
            if (this.showNullNodes) {
                me = node(String.format("null%d", duplicateNodeNumber++)).with(Shape.RECTANGLE, Label.of("null"));
            }
        }
        if (me != null) {
            this.nodes.add(me);
            if (parent != null) {
                parent = parent.link(to(me));
            }
        }
        return parent;
    }

    /**
     * creates the graphviz from the nodes
     */
    public void createGraphviz() {
        this.graphviz = Graphviz.fromGraph(graph().nodeAttr().with(Font.name(this.font)).with(this.getNodes()).graphAttr().with(Color.rgba("00000000").background()));
    }

    /**
     * creates a JavaFX image with grass as background and the graphviz in the foreground
     *
     * @return a JavaFX image
     */
    @NotNull
    public Image getGraphvizImage() {
        if (this.graphviz == null)
            throw new RuntimeException("Call createGraphviz before getGraphvizImage");

        BufferedImage graphviz = this.graphviz.render(Format.SVG).toImage();

        BufferedImage combined;
        if (this.withGrass) {
            BufferedImage grass = null;
            try {
                grass = ImageIO.read(getClass().getResource("grass_PNG10856.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            combined = new BufferedImage(graphviz.getWidth(), graphviz.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics g = combined.getGraphics();
            if (grass != null) {
                for (int i = 0; i < Math.ceil(graphviz.getWidth() / (double) grass.getWidth()); ++i) {
                    g.drawImage(grass, i * grass.getWidth(), 0, null);
                }
            }
            g.drawImage(graphviz, 0, 0, null);
        } else {
            combined = graphviz;
        }

        return SwingFXUtils.toFXImage(combined, null);
    }

    /**
     * saves the graphviz
     *
     * @param file   where to save
     * @param format the format
     * @throws IOException not possible to save the file
     */
    public void saveGraphviz(File file, Format format) throws IOException {
        if (this.graphviz == null)
            throw new RuntimeException("Call createGraphviz before saveGraphviz");

        this.graphviz.render(format).toFile(file);
    }
}
