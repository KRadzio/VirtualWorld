#pragma once
#include "Animal.h"

#define STRENGTH 2
#define INITIATIVE 1
#define SYMBOL 'Z'

class Turtle : public Animal
{
public:
    Turtle(World &wR, const int &xPos, const int &yPos);
    ~Turtle();
    bool specialAbility(Organism *other) override;
     virtual void takeAction() override;
};