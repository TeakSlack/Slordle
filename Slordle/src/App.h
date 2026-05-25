#ifndef APP_H
#define APP_H

#include "Window.h"

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
};

#endif // APP_H