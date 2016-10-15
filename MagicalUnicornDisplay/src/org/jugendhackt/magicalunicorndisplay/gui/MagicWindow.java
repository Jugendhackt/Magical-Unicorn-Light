package org.jugendhackt.magicalunicorndisplay.gui;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.libffi.Closure;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.net.DatagramPacket;

import org.jugendhackt.magicalunicorndisplay.gui.utils.Color;
import org.jugendhackt.magicalunicorndisplay.net.DataRecievedInterface;
import org.jugendhackt.magicalunicorndisplay.net.UDPConnector;

public class MagicWindow {
	
	public static int PORT = 1337;
	
	long window;

	public String title;
	
	protected Color clearColor;
	
	GLCapabilities caps;
	
	GLFWErrorCallback errorCallback;
	GLFWKeyCallback keyCallback;
	GLFWMouseButtonCallback mouseCallback;
	GLFWCursorPosCallback cursor_pos_callback;
	GLFWCursorEnterCallback cursor_enter_callback;
	GLFWScrollCallback scroll_callback;
	GLFWWindowFocusCallback focusCallback;
	GLFWFramebufferSizeCallback fsCallback;
	Closure debugProc;

	Object lock = new Object();
	boolean destroyed;
	
	int monID;
	
	int width, height;
	UDPConnector server;
	
	public MagicWindow (int monitor) {
		monID = monitor;
		title = "Magic Unicorn Window";
		
		clearColor = new Color (0,0,0,1);
	}
	
	public void run() {
		try {
			init();
			winProcLoop();

			synchronized (lock) {
				destroyed = true;
				glfwDestroyWindow(window);
			}
			if (debugProc != null)
				debugProc.release();
			//keyCallback.release();
		} finally {
			glfwTerminate();
//			errorCallback.release();
		}
	}

	void init() {
		//glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		if (glfwInit() != GL11.GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");
		
		//glfwSetWindowFocusCallback(window, window_focus_callback);
		
		if (monID >= GLFW.glfwGetMonitors().capacity()) {
			monID = GLFW.glfwGetMonitors().capacity() -1;
		} else if (monID < 0) {
			monID = 0;
		}
		
		long monitor = GLFW.glfwGetMonitors().get(monID);
		
		GLFWVidMode vidmode = glfwGetVideoMode(monitor);
		
		width = vidmode.width();
		height = vidmode.height();
		
		window = glfwCreateWindow(vidmode.width(),vidmode.height(), title, monitor, NULL);
		
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				onKey (window, key, scancode, action, mods);
			}
		});

		glfwSetWindowPos(window, 0, 0);
		glfwShowWindow(window);
		
	}

	public void onKey (long window, int key, int scancode, int action, int mods) {
		if (key ==  GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
			close ();
	}
	
	void renderLoop() {
		glfwMakeContextCurrent(window);
		caps = GL.createCapabilities(destroyed);
		
		onLoad ();
		//debugProc = GLUtil.setupDebugMessageCallback();
		
		while (!destroyed) {
			glViewport(0, 0, width, height);
			glClearColor(this.clearColor.r, this.clearColor.g, this.clearColor.b, this.clearColor.a);
			glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
			synchronized (lock) {
				if (!destroyed) {
					glfwSwapBuffers(window);
				}
			}
		}
		
	}
	
	public void setClearColor (Color color) {
		this.clearColor = color;
	}

	public void setClearColor (float r, float g, float b) {
		setClearColor (new Color (r,g,b,1));
	}
	
	public void hideWindow () {
		glfwHideWindow(window);
	}
	
	public void showWindow () {
		glfwShowWindow(window);
	}

	public GLCapabilities getGLCapabilities() {
		return caps;
	}

	public long getMonitor () {
		return GLFW.glfwGetWindowMonitor(window);
	}
	
	public void onLoad () {
		setClearColor(0.6f, 0.8f, 1f);
		glClearColor(this.clearColor.r, this.clearColor.g, this.clearColor.b, this.clearColor.a);
		startServer ();
	}

	public void startServer () {
		UDPConnector server = new UDPConnector (PORT);
		server.setPacketSize(512);
		
		server.addRecievedCallback(new DataRecievedInterface() {
			
			@Override
			public void onPacketReceived(DatagramPacket packet, UDPConnector server) {
				byte[] data = packet.getData();
				System.out.println("Reciecieved !");
//				if (data.length > 2) {
					setClearColor(new Color(data[0], data[1], data[2]));
//				}
			}
		});
		server.setPort(PORT);
		server.start();
		
		
	}
	
	public void close () {
		GLFW.glfwSetWindowShouldClose(window, GL_TRUE);
	}

	public void iconify () {
		GLFW.glfwIconifyWindow(window);
	}
	
	void winProcLoop() {
		/*
		 * Start new thread to have the OpenGL context current in and which does
		 * the rendering.
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				renderLoop();
			}
		}).start();
		
		// Input loop
		while (glfwWindowShouldClose(window) == GL_FALSE) {
			glfwWaitEvents();
			
			
		}
	}
}
