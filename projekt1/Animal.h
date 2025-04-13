#pragma once
#include "Organism.h"
#define DEFAULT_MOVE_RANGE 1
#define REPRODUCE_AGE 5

class Animal : public Organism
{
public:
    Animal(World &wR, const int &xPos, const int &yPos, const char &symbol, const int &strength, const int &initiative, AllOrganisms type);
    ~Animal();
    virtual void takeAction() override;
    virtual void colision(Organism *other) override;
    virtual bool isAnAnimal() const override;

protected:
    bool isTheSameSpecies(Organism *other) const;
    void reproduce(Organism *other);
    void move(int newX, int newY);
    int moveRange = DEFAULT_MOVE_RANGE;
};