#pragma once
#include "Plant.h"

#define SYMBOL 'w'
#define STRENGTH 99

class Nightshade : public Plant
{
public:
    public:
    Nightshade(World &wR, const int &xPos, const int &yPos);
    ~Nightshade();
     bool specialAbility(Organism *other) override;
};