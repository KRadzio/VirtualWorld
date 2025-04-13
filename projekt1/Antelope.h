#pragma once
#include "Animal.h"

#define STRENGTH 4
#define INITIATIVE 4
#define SYMBOL 'A'
#define MOVE_RANGE 2

class Antelope : public Animal
{
public:
    Antelope(World &wR, const int &xPos, const int &yPos);
    ~Antelope();
    bool specialAbility(Organism *other) override;
    virtual void takeAction() override;
};