#ifndef APP_H
#define APP_H

#include "Window.h"
#include "Input.h"
#include "Event.h"
#include "Scene/SceneManager.h"

class App
{
public:
	App() {}
	~App() {}
	void Run();

private:
	void Init();
	void Shutdown();

	Window m_Window{ 1280, 720, "Slordle" };
	EventBus m_Bus;
	Input m_Input{ m_Bus };
	SceneManager m_SceneManager;
};

#endif // APP_H