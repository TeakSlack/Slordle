#ifndef INPUT_H
#define INPUT_H

#include "Event.h"

class Input
{
public:
	Input(EventBus& bus) : m_Bus(bus) {}
	void Poll();

private:
	void PollKeyboard();

	EventBus& m_Bus;
};

#endif // INPUT_H