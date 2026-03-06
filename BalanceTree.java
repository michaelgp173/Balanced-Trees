public interface BalanceTree<E extends Comparable<E>> {
    public void insert(E item);
    public boolean find(E item);
    public void delete(E item);
    public int height();
}
