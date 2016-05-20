package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveGame {

	static String saveLocation = "gameModel.ser";

	public static void saveGame(GameModel model) throws IOException {
		try {
			FileOutputStream fileOut = new FileOutputStream(saveLocation);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(model);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			throw new IOException();
		}
	}

	public static GameModel restartGame() throws IOException {

		GameModel model = null;
		try {
			FileInputStream fileIn = new FileInputStream(saveLocation);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			model = (GameModel) in.readObject();
			in.close();
			fileIn.close();

		} catch (IOException i) {
			//i.printStackTrace();
			throw new IOException();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return model;
	}
}
