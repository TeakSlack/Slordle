#ifndef MENU_SCENE_H
#define MENU_SCENE_H

#include "Scene/IScene.h"

class MenuScene : public IScene
{
	void Update(float deltaTime);
	void Render();
};

#endif // MENU_SCENE_H