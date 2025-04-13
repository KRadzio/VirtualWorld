#pragma once
#include "Plant.h"

#define SYMBOL 'g'
#define STRENGTH_BONUS 3
#define STRENGTH 0

class Guarana : public Plant
{
public:
    public:
    Guarana(World &wR, const int &xPos, const int &yPos);
    ~Guarana();
    bool specialAbility(Organism *other) override;
};