#pragma once
#include "Plant.h"

#define SYMBOL 'b'
#define STRENGTH 10

class PineBorscht : public Plant
{
public:
public:
    PineBorscht(World &wR, const int &xPos, const int &yPos);
    ~PineBorscht();
    virtual void takeAction() override;
    bool specialAbility(Organism *other) override;
};