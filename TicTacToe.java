import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TicTacToe extends JFrame {
	private static int turn;
	private JLabel status;
	ArrayList<GameButton> btns = new ArrayList<GameButton>();
	
	public TicTacToe(){
		turn = 1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel btnPanel = new JPanel(new GridLayout(3,3));
		status = new JLabel(getTurn() + "'s turn");
		add(status, BorderLayout.SOUTH);
		for (int i = 1 ; i < 10 ; i++){
			GameButton btn = new GameButton(i);
			btn.addActionListener(new ActionListener(){
				@Override 
					public void actionPerformed(ActionEvent e) {
						GameButton btn = (GameButton)e.getSource();
						btn.setIsSelected(true);
						btn.setEnabled(false);
						btn.setText(getTurn());
						
						if(makeDecision(getTurn())) {
							status.setText(getTurn() + "'is the winner");
							setEnabledAll(false);
							return;
						}
						turn ++;
						status.setText(getTurn() + "'s turn");
					}
				});
			btnPanel.add(btn);
			btns.add(btn);
		}
		add(btnPanel);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener(){
			@Override
				public void actionPerformed(ActionEvent e){
					turn = 1;
					status.setText(getTurn() + "'s turn");
					setEnabledAll(true);
			}
			});
		
		add(btnReset, BorderLayout.NORTH);

		
		setSize(200,200);
		
		
		
	}
	
	
	
	
	public String getTurn(){
		return (turn%2 != 0)?"X":"O";
	}
	
	
	public boolean makeDecision(String currentPlayer){
		
		for (GameButton b : btns) {
			if(b.getId() == 1 && b.getIsSelectedByCurrentPlayer(currentPlayer) ){
				//23  
				if(btns.get(1).getIsSelectedByCurrentPlayer(currentPlayer) && 
					btns.get(2).getIsSelectedByCurrentPlayer(currentPlayer) ) {
						setWinner(b);
						setWinner(btns.get(1));
						setWinner(btns.get(2));
						return true;
					} 
				//47  
				if(btns.get(3).getIsSelectedByCurrentPlayer(currentPlayer) && 
					btns.get(6).getIsSelectedByCurrentPlayer(currentPlayer) ) {
						setWinner(b);
						setWinner(btns.get(3));
						setWinner(btns.get(6));
						return true;
					} 
				//59 
				if(btns.get(4).getIsSelectedByCurrentPlayer(currentPlayer) && 
					btns.get(8).getIsSelectedByCurrentPlayer(currentPlayer) ) {
						setWinner(b);
						setWinner(btns.get(4));
						setWinner(btns.get(8));
						return true;
					}
				
				} else if (b.getId() == 2  && b.getIsSelectedByCurrentPlayer(currentPlayer)) {
					//58 
					if(btns.get(4).getIsSelectedByCurrentPlayer(currentPlayer) && 
						btns.get(7).getIsSelectedByCurrentPlayer(currentPlayer) ) {
							setWinner(b);
							setWinner(btns.get(4));
							setWinner(btns.get(7));
							return true;
						}
					
					} else if (b.getId() == 3  && b.getIsSelectedByCurrentPlayer(currentPlayer)) {
						//57
						if(btns.get(4).getIsSelectedByCurrentPlayer(currentPlayer) && 
							btns.get(6).getIsSelectedByCurrentPlayer(currentPlayer) ) {
								setWinner(b);
								setWinner(btns.get(4));
								setWinner(btns.get(6));
								return true;
							}
						//69 
						if(btns.get(5).getIsSelectedByCurrentPlayer(currentPlayer) && 
							btns.get(8).getIsSelectedByCurrentPlayer(currentPlayer) ) {
								setWinner(b);
								setWinner(btns.get(5));
								setWinner(btns.get(8));
								return true;
							}
						
						
						} else if (b.getId() == 4  && b.getIsSelectedByCurrentPlayer(currentPlayer)){
							//56
							if(btns.get(4).getIsSelectedByCurrentPlayer(currentPlayer) && 
								btns.get(5).getIsSelectedByCurrentPlayer(currentPlayer) ) {
									setWinner(b);
									setWinner(btns.get(4));
									setWinner(btns.get(5));
									return true;
								}
							} else if (b.getId() == 7  && b.getIsSelectedByCurrentPlayer(currentPlayer)) {
								//89
								if(btns.get(7).getIsSelectedByCurrentPlayer(currentPlayer) && 
									btns.get(8).getIsSelectedByCurrentPlayer(currentPlayer) ) {
										setWinner(btns.get(7));
										setWinner(btns.get(8));
										setWinner(b);
										return true;
									}
								}
				
			
		}
		
		return false;
	}
	
	
	public void setEnabledAll(boolean status) {

		for(GameButton btn : btns) {
			if(status) {
				btn.setEnabled(true);
				btn.setText("");
				btn.setOpaque(false);
			}
			btn.setEnabled(status);
		}
	}
	
	public void setWinner(GameButton btn){
		btn.setEnabled(true);
		btn.setBackground(Color.BLUE);
		btn.setOpaque(true);
	}
	
	
	public static void main(String[]args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override 
				public void run(){
					TicTacToe game = new TicTacToe();
					game.setVisible(true);
				}
			});
		
	}
}



class GameButton extends JButton {
	private int id ;
	private boolean isSelected;
	public GameButton(int id){
		super();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	public boolean getIsSelected(){
		return isSelected;
	}
	public void setIsSelected(boolean val){
		isSelected = val;
	}
	public boolean getIsSelectedByCurrentPlayer(String player){
		return getText().equals(player);
	}
}

