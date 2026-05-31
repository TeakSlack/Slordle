#ifndef ISCENE_H
#define ISCENE_H

class IScene
{
public:
	virtual ~IScene() = default;
	virtual void Update(float deltaTime) = 0;
	virtual void Render() = 0;
};

#endif // ISCENE_H