class Node 
{ 
    int key, height; 
    Node left, right; 
  
    Node(int d) 
    { 
        key = d; 
        height = 1; 
    } 
} 
  
class Avltree 
{ 
    Node root; 
  
 
    int height(Node N) 
    { 
        if (N == null) 
            return 0; 
        return N.height; 
    } 
  
   
    int max(int a, int b) 
    { 
        return (a > b) ? a : b; 
    } 
  
    
    Node rightRotate(Node y) 
    { 
        Node x = y.left; 
        Node T2 = x.right; 
  
        
        x.right = y; 
        y.left = T2; 
  
       
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
       
        return x; 
    } 
  
   
    Node leftRotate(Node x) 
    { 
        Node y = x.right; 
        Node T2 = y.left; 
  
        
        y.left = x; 
        x.right = T2; 
  
       
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        
        return y; 
    } 
  
     
    int getBalance(Node N) 
    { 
        if (N == null) 
            return 0; 
        return height(N.left) - height(N.right); 
    } 
  
    Node insert(Node node, int key) 
    { 
      
        if (node == null) 
            return (new Node(key)); 
  
        if (key < node.key) 
            node.left = insert(node.left, key); 
        else if (key > node.key) 
            node.right = insert(node.right, key); 
        else  
            return node; 
  
       
        node.height = 1 + max(height(node.left), 
                            height(node.right)); 
  
      
        int balance = getBalance(node); 
  
       
        if (balance > 1 && key < node.left.key) 
            return rightRotate(node); 
  
       
        if (balance < -1 && key > node.right.key) 
            return leftRotate(node); 
  
       
        if (balance > 1 && key > node.left.key) 
        { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
       
        if (balance < -1 && key < node.right.key) 
        { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        
        return node; 
    } 
  
  
    Node minValueNode(Node node) 
    { 
        Node current = node; 
  
    
        while (current.left != null) 
        current = current.left; 
  
        return current; 
    } 
  
    Node deleteNode(Node root, int key) 
    { 
        
        if (root == null) 
            return root; 
  
   
        if (key < root.key) 
            root.left = deleteNode(root.left, key); 
  
       
        else if (key > root.key) 
            root.right = deleteNode(root.right, key); 
  
        else
        { 
  
            if ((root.left == null) || (root.right == null)) 
            { 
                Node temp = null; 
                if (temp == root.left) 
                    temp = root.right; 
                else
                    temp = root.left; 
  
               
                if (temp == null) 
                { 
                    temp = root; 
                    root = null; 
                } 
                else  
                    root = temp; 
                              
            } 
            else
            { 
  
                
                Node temp = minValueNode(root.right); 
  
                
                root.key = temp.key; 
 
                
                root.right = deleteNode(root.right, temp.key); 
            } 
        } 
  
       
        if (root == null) 
            return root; 
  
        
        root.height = max(height(root.left), height(root.right)) + 1; 
  
       
        int balance = getBalance(root); 
  
        if (balance > 1 && getBalance(root.left) >= 0) 
            return rightRotate(root); 
  
        if (balance > 1 && getBalance(root.left) < 0) 
        { 
            root.left = leftRotate(root.left); 
            return rightRotate(root); 
        } 
  
        if (balance < -1 && getBalance(root.right) <= 0) 
            return leftRotate(root); 
  
        if (balance < -1 && getBalance(root.right) > 0) 
        { 
            root.right = rightRotate(root.right); 
            return leftRotate(root); 
        } 
  
        return root; 
    } 
  

    void postOrder(Node node) 
    { 
        if (node != null) 
        {   postOrder(node.left); 
            postOrder(node.right);
            System.out.print(node.key + " "); 
           
            
        } 
    } 
  
    public static void main(String[] args) 
    { 
        Avltree tree = new Avltree(); 
  
       
        tree.root = tree.insert(tree.root, 13);
        tree.root = tree.insert(tree.root, 8);
        tree.root = tree.insert(tree.root, 18);
        tree.root = tree.insert(tree.root, 23);
        tree.root = tree.insert(tree.root, 16);
        tree.root = tree.insert(tree.root, 7);
        tree.root = tree.insert(tree.root, 26);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 21);

  
        System.out.println("Postorder traversal of "+ 
                            "constructed tree is : "); 
        tree.postOrder(tree.root); 
       
  
        tree.root = tree.deleteNode(tree.root, 7); 
        tree.root = tree.deleteNode(tree.root, 26); 
        tree.root = tree.deleteNode(tree.root, 21); 
  
        System.out.println(""); 
        System.out.println("Postorder traversal after "+ 
                        "deletion of 7, 26, 21:"); 
        tree.postOrder(tree.root); 
    } 
} 
 
