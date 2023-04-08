package stud.g01.solver;

import core.problem.Problem;
import core.solver.algorithm.heuristic.Predictor;
import core.solver.algorithm.searcher.AbstractSearcher;
import core.solver.queue.Frontier;
import core.solver.queue.Node;

import java.util.Deque;
import java.util.Stack;

public class IdAStar extends AbstractSearcher {
    private final Predictor predictor;
    private final Stack<Node> openStack;
    public IdAStar(Predictor predictor) {
        super();
        this.predictor = predictor;
        openStack = new Stack<Node>();
        //closeStack = new HashMap<Integer, Integer>();
    }

    @Override
    public Deque<Node> search(Problem problem) {
        if (!problem.solvable()){
            return null;
        }
        //��ȡ���ڵ�
        openStack.clear();
        //closeStack.clear();
        Node root = problem.root(predictor);
        int cutoff = root.evaluation();

        //���������
        int maxIteratorDepth = 70;
        while (cutoff < maxIteratorDepth) {
            openStack.push(root);
            //��һ�ֵ����Ĳü���ֵ
            int newCutoff = cutoff;
            //��ջδ��ʱ������ִ�д��ü�ֵ�������������
            //ͳ����չ�����
            while (!openStack.empty()) {
                Node node = openStack.pop();

                //���²ü�ֵΪδ��̽���ڵ�����С������ֵ
                if (problem.goal(node.getState())) {
                    return generatePath(node);
                }
                //��С�ڵ��ڲü�ֵʱ�������������
                for (Node child : problem.childNodes(node, predictor)) {
                    //��֦����ֹ�ڵ�̽���ص����ڵ�
                    if (child.evaluation() <= cutoff) {
                        if (node.getParent() == null || !node.getParent().equals(child)) {
                            openStack.push(child);
                        }
                    } else {
                        //��¼���ڵ�ǰcutoff����Сֵ
                        newCutoff = (newCutoff > cutoff) ? (Math.min(child.evaluation(), newCutoff)) : child.evaluation();
                        //System.out.println("cutoff="+cutoff+" newcutoff="+newCutoff+" child.f="+child.evaluation());
                    }
                }
            }
            cutoff = newCutoff;
        }
        return null;
    }
}