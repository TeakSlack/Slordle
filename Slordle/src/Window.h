#ifndef WINDOW_H
#define WINDOW_H

#include "Log.h"

class Window
{
public:
	Window(int width, int height, const std::string& title);
	~Window();

	bool ShouldClose()	const;
	int  GetWidth()		const;
	int  GetHeight()	const;
};

#endif // WINDOW_H